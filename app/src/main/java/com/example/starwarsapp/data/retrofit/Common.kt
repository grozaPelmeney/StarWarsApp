package com.example.starwarsapp.data.retrofit

object Common {
    private const val base_url = "https://swapi.dev/api/"
    val apiServices: ApiServices
        get() = RetrofitBuilder.getRetrofit(base_url).create(ApiServices::class.java)
}