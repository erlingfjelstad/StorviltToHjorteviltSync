package org.example

import kotlinx.datetime.toKotlinLocalDateTime
import org.example.models.Art
import org.example.models.Storvilt
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun parseCsvToStorvilt(filePath: String): List<Storvilt> {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

    val storviltList = mutableListOf<Storvilt>()

    File(filePath).forEachLine { line ->
        // Skip the header
        if (line.startsWith("StorviltID")) return@forEachLine

        val fields = line.split(";")
        val storviltId = fields[0].toInt()
        val kategoriSkutt = fields[1].toInt()
        val skuttDato = LocalDateTime.parse(fields[2], formatter)
        val settOgSkuttUid = fields[3].ifEmpty { null }
        val merkelappnummer = fields[4].ifEmpty { null }
        val art = Art.getById(fields[5].toIntOrNull())
        val slaktevekt = fields[6].replace(",", ".").toDoubleOrNull()
        val kontrollertVekt = fields[7].replace(",", ".").toDoubleOrNull()
        val jaktfeltId = fields[8].ifEmpty { null }
        val storvilt = Storvilt(
            storviltId,
            kategoriSkutt,
            skuttDato.toKotlinLocalDateTime(),
            settOgSkuttUid,
            merkelappnummer,
            art,
            slaktevekt,
            kontrollertVekt,
            jaktfeltId
        )

        storviltList.add(storvilt)
    }
    return storviltList
}