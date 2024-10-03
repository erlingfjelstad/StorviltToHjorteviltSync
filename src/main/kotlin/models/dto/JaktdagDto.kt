package org.example.models.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class JaktDagDto @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("Id") val id: String,
    @SerialName("Dato") val dato: LocalDate,
    @SerialName("Sesong") val sesong: Int,
    @SerialName("Art") val art: String,
    @SerialName("Jegernummer") val jegernummer: Int?,
    @SerialName("AntallTimerJaktet") val antallTimerJaktet: Int,
    @SerialName("AntallJegere") val antallJegere: Int,
    @SerialName("SettOkseEllerBukk") val settOkseEllerBukk: Int,
    @SerialName("SettKyrUtenKalvEllerKolle") val settKyrUtenKalvEllerKolle: Int,
    @SerialName("SettSpissbukk") val settSpissbukk: Int,
    @SerialName("SettKyrMed1Kalv") val settKyrMed1Kalv: Int,
    @SerialName("SettKyrMed2EllerFlereKalver") val settKyrMed2EllerFlereKalver: Int,
    @SerialName("SettKalverAlene") val settKalverAlene: Int,
    @SerialName("SettUkjente") val settUkjente: Int,
    @SerialName("Jaktfelt") val jaktfelt: String,
    @JsonNames("Individer", "SkutteDyr") val skutteDyrDto: List<IndividDto>?
)