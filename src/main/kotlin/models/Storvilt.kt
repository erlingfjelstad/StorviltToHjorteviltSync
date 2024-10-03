package org.example.models

import kotlinx.datetime.LocalDateTime


data class Storvilt(
    val storviltId: Int,
    val kategoriSkutt: Int,
    val skuttDato: LocalDateTime,
    val settOgSkuttUid: String?,
    val merkelappnummer: String?,
    val art: Art,
    val slaktevekt: Double?,
    val kontrollertVekt: Double?,
    val jaktfeltId: String?,
)