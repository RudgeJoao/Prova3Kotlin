package br.com.fiap.checkpoint.data

import br.com.fiap.checkpoint.domain.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v1/paises/todos")
    fun getCountries() : Call<List<CountryModel>>
}