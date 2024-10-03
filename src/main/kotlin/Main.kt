package org.example

import io.ktor.client.call.*
import io.ktor.client.request.*
import org.example.models.Art
import org.example.models.Storvilt
import org.example.models.dto.HjorteviltElgDto
import org.example.models.dto.HjorteviltHjortDto
import org.example.models.dto.JaktDagDto
import org.example.models.dto.TerrengDto


suspend fun main() {
    val storvilt = parseCsvToStorvilt("StorviltRapport.csv")
        .filter { storvilt ->
            storvilt.settOgSkuttUid != null // settOgSkuttUid tilsvarer id for et skutt dyr i Hjorteviltregisteret
        }.filter { storvilt ->
            storvilt.jaktfeltId != null && !storvilt.jaktfeltId.contentEquals("err", true) // Filtrerer ut alle jaktfelt som er null eller har "err" som jaktfelt navn
        }

    storvilt.onEach { storvilt ->
        println("---------------------")
        println("START for storvilt: ${storvilt.storviltId}")
        println("---------------------")

        /**
         * Vi må sjekke om et skutt dyr allerede finnes i Hjorteviltregisteret først.
         * Vi sender en request til v0/individer med settOgSkuttUid (altså "id" i Hjorteviltregisteret)
         * Hvis det skutte dyret eksisterer (altså Hjorteviltregisteret returnerer http status kode 200..299) så antar vi at det finnes
         * Hvis Hjorteviltregisteret returnerer http status kode som *ikke* er OK så fortsetter vi.
         */
        val existInHjortevilt = checkIfStorviltExistInHjorteviltRegisteret(storvilt)

        if (!existInHjortevilt) {
            /**
             * Hvis skutte dyret ikke finnes i Hjorteviltregisteret vil vi hente Jaktdag basert på skuttdatoen på det skutte dyret
             * Hvis det ikke finnes en jaktdag basert på skuttdatoen så sender vi ingenting
             * Hvis det finnes en jaktdag for skuttdatoen så fortsetter vi
             */
            val jaktdagDto: JaktDagDto? = getJaktdagFromHjorteviltRegisteret(storvilt)

            if (jaktdagDto != null) {
                /**
                 * Hvis vi fant en match på jaktdag basert på skuttdato så vil vi prøve å sende et skuttdyr til denne jaktdagen
                 * Hvis skutte dyret sin art er Elg så sender vi en HjorteviltElgDto , hvis arten er Hjort sender vi en HjorteviltHjortDto
                 */
                postStorviltToHjorteviltRegsiteret(storvilt, jaktdagDto)
            }
        }

        println("---------------------")
        println("STOP for storvilt: ${storvilt.storviltId}")
        println("---------------------")
    }
}

suspend fun checkIfStorviltExistInHjorteviltRegisteret(storvilt: Storvilt): Boolean {
    val response = httpClient.get(HJORTEVILT_BASE_API_URL.plus("v0/individer/${storvilt.settOgSkuttUid}"))
    println("storviltId: ${storvilt.storviltId}, statusCode: ${response.status.value}")
    return response.status.value in 200..299
}

suspend fun getJaktdagFromHjorteviltRegisteret(storvilt: Storvilt): JaktDagDto? {
    val jaktdagResponse = httpClient.get(HJORTEVILT_BASE_API_URL.plus("v0/jaktdager/${storvilt.jaktfeltId}/2024"))
    return jaktdagResponse.body<List<JaktDagDto>>().firstOrNull { dto ->
        dto.dato == storvilt.skuttDato.date
    }
}

suspend fun postStorviltToHjorteviltRegsiteret(storvilt: Storvilt, dto: JaktDagDto) {
    httpClient.post(HJORTEVILT_BASE_API_URL.plus("v0/individer")) {
        when (storvilt.art) {
            Art.Elg -> setBody(
                /**
                 * Se inne i konstruktøren for alle felter vi sender
                 */
                HjorteviltElgDto(
                    id = storvilt.settOgSkuttUid!!,
                    dato = storvilt.skuttDato.date,
                    jaktdagId = dto.id,
                    art = storvilt.art.name,
                    kategoriSkutt = storvilt.kategoriSkutt,
                    jaktfelt = dto.jaktfelt,
                    slaktevekt = storvilt.slaktevekt?.toInt(),
                    kjevelapp = storvilt.merkelappnummer
                )
            )

            Art.Hjort -> setBody(
                HjorteviltHjortDto(
                    /**
                     * Se inne i konstruktøren for alle felter vi sender
                     */
                    id = storvilt.settOgSkuttUid!!,
                    dato = storvilt.skuttDato.date,
                    jaktdagId = dto.id,
                    art = storvilt.art.name,
                    kategoriSkutt = storvilt.kategoriSkutt,
                    terreng = TerrengDto.UTMARK,
                    jaktfelt = dto.jaktfelt,
                    slaktevekt = storvilt.slaktevekt?.toInt(),
                    kjevelapp = storvilt.merkelappnummer,
                )
            )
        }
    }
}


