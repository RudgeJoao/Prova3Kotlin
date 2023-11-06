package br.com.fiap.checkpoint.domain

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("nome")
    val nome: CountryName,

    @SerializedName("localizacao")
    val localizacao: CountryContinent,


    @SerializedName("governo")
    val governo: CountryGovernment
)

data class CountryName(
    @SerializedName("abreviado")
    val abreviado: String
)

data class CountryContinent(
    @SerializedName("regiao")
    val regiao: CountryContinentRegion
)

data class CountryContinentRegion(
    @SerializedName("nome")
    val nome: String
)

data class CountryGovernment(
    @SerializedName("capital")
    val capital: CountryGovernmentCapital
)

data class CountryGovernmentCapital(
    @SerializedName("nome")
    val nome: String
)
