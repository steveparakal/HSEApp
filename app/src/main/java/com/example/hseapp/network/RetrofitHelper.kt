package com.example.hseapp.network

import com.example.hseapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    var apiService: APIService? = null
    fun getInstance(): APIService {
        if(apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiService = retrofit.create(APIService::class.java)
        }
        return apiService!!
    }
}