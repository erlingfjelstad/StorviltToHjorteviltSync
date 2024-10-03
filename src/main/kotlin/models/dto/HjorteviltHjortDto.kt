package org.example.models.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HjorteviltHjortDto(
    @SerialName("Id") val id: String,
    @SerialName("JaktdagId") val jaktdagId: String,
    @SerialName("Art") val art: String,
    @SerialName("Alder") val alder: String,
    @SerialName("Kjønn") val kjonn: String,
    @SerialName("Dato") val dato: LocalDate,
    @SerialName("Terreng") val terreng: String,
    @SerialName("Jaktfelt") val jaktfelt: String,
    @SerialName("SlaktevektKg") val slaktevekt: Int?,
    @SerialName("MålemetodeSlaktevekt") val malemetodeSlaktevekt: String?,
    @SerialName("Merkelapp") val kjevelapp: String?,
) {
    constructor(
        id: String,
        dato: LocalDate,
        jaktdagId: String,
        art: String,
        kategoriSkutt: Int?,
        terreng: TerrengDto,
        jaktfelt: String,
        slaktevekt: Int?,
        kjevelapp: String?,
    ) : this(
        id = id,
        jaktdagId = jaktdagId,
        art = art,
        alder = when (kategoriSkutt) {

            in 10..19 -> {
                "Kalv"
            }

            in 20..29 -> {
                "Ungdyr"
            }

            else -> "Voksen"
        },
        kjonn = when (kategoriSkutt) {
            12, 22, 32 -> "Hunn"
            else -> "Hann"
        },
        dato = dato,
        terreng = when (terreng) {
            TerrengDto.INNMARK -> "Innmark"
            TerrengDto.UTMARK -> "Utmark"
        },
        slaktevekt = slaktevekt,
        kjevelapp = kjevelapp,
        malemetodeSlaktevekt = if (slaktevekt != null) {
            "Veid"
        } else null,
        jaktfelt = jaktfelt
    )
}

