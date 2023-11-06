package br.com.fiap.checkpoint.shared

import br.com.fiap.checkpoint.data.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyFactory {
    private const val BASE_URL: String = "https://servicodados.ibge.gov.br/api/"

    private lateinit var INSTANCE: Retrofit

    private fun getRetrofitInstance() : Retrofit {
        val http = OkHttpClient.Builder()
        if (!::INSTANCE.isInitialized){
            INSTANCE = Retrofit.Builder()
                .baseUrl("https://servicodados.ibge.gov.br/api/")
                .client(http.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return INSTANCE
    }

    fun createPostService(): ApiService{
        return getRetrofitInstance().create(ApiService::class.java)
    }
}