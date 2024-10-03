package org.example.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndividDto(
    @SerialName("Uri")
    val uri: String? = null,

    @SerialName("Id")
    val id: String,

    @SerialName("Dato")
    val dato: String? = null,

    @SerialName("UtenforJakttid")
    val utenforJakttid: Boolean? = null,

    @SerialName("SkuttIOvervåkområde")
    val skuttIOvervakomrade: Boolean? = null,

    @SerialName("Sesong")
    val sesong: Int? = null,

    @SerialName("Art")
    val art: String? = null,

    @SerialName("Alder")
    val alder: String? = null,

    @SerialName("Kjønn")
    val kjonn: String? = null,

    @SerialName("Registrator")
    val registrator: String? = null,

    @SerialName("Jegernummer")
    val jegernummer: Int? = null,

    @SerialName("RegistrertAv")
    val registrertAv: String? = null,

    @SerialName("RegistrertDato")
    val registrertDato: String? = null,

    @SerialName("SistEndretAv")
    val sistEndretAv: String? = null,

    @SerialName("SistEndretDato")
    val sistEndretDato: String? = null,

    @SerialName("Merkelapp")
    val merkelapp: String? = null,

    @SerialName("Kontrollkort")
    val kontrollkort: String? = null,

    @SerialName("GevirtakkerVenstre")
    val gevirtakkerVenstre: Int? = null,

    @SerialName("GevirtakkerHøyre")
    val gevirtakkerHoyre: Int? = null,

    @SerialName("MålemetodeSlaktevekt")
    val malemetodeSlaktevekt: String? = null,

    @SerialName("SlaktevektKg")
    val slaktevektKg: Int? = null,

    @SerialName("Terreng")
    val terreng: String? = null,

    @SerialName("AntallKalverIfølgeMedHunndyret")
    val antallKalverIfolgeMedHunndyret: Int? = null,

    @SerialName("FeltKalvIkkeIfølgeMedMorDyr")
    val feltKalvIkkeIfolgeMedMorDyr: Boolean? = null,

    @SerialName("MelkIJuret")
    val melkIJuret: Boolean? = null,

    @SerialName("Fellingssted")
    val fellingssted: String? = null,

    @SerialName("Koordinatfesting")
    val koordinatfesting: String? = null,

    @SerialName("KoordinatØst")
    val koordinatOst: String? = null,

    @SerialName("KoordinatNord")
    val koordinatNord: String? = null,

    @SerialName("LevertHjerneprøve")
    val levertHjerneprove: Boolean? = null,

    @SerialName("Merknad")
    val merknad: String? = null,

    @SerialName("Godkjent")
    val godkjent: Boolean? = null,

    @SerialName("JaktdagId")
    val jaktdagId: String? = null,

    @SerialName("Jaktfelt")
    val jaktfelt: String? = null,

    @SerialName("Vald")
    val vald: String? = null,

    @SerialName("Villreinvald")
    val villreinvald: String? = null,

    @SerialName("CWDPrøvesvar")
    val cwdProvesvar: String? = null,

    @SerialName("EksaktAlder")
    val eksaktAlder: Int? = null
)