package com.example.assignmentsephora.di

import com.example.assignmentsephora.service.SephoraRepository
import com.example.assignmentsephora.service.SephoraService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyUtil {

    private fun provideNetworkURL(): String = "https://api.sephora.sg/api/v2.5/"

    private fun provideOKHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder()
                    .header("X-OS-Name", "android")
                    .header("X-App-Platform", "mobileapp_android")
                    .header("X-Platform", "mobile_app")
                    .header("X-Site-Country", "SG")
                    .header("Accept-Language", "en-SG")
                val request = builder.build()
                chain.proceed(request)
            }
            .build()
        return httpClient
    }

    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideNetworkURL())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOKHttp())
            .build()
    }

    private fun provideSephoraService(): SephoraService {
        return provideRetrofitInstance().create(SephoraService::class.java)
    }

    fun provideSephoraRepository() = SephoraRepository(provideSephoraService())
}