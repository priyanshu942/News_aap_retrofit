package com.example.newsforall

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=5752535f04344f378cf6a6c660b917a5
const val baseurl="https://newsapi.org/"
const val key="5752535f04344f378cf6a6c660b917a5"
interface getdata {

    @GET("v2/top-headlines?apiKey=$key")

    fun data(@Query("country")country:String, @Query("page")page:Int):Call<Newdata>

    object NewsService{
        val instance:getdata
        init{

            val retrofit: Retrofit =  Retrofit.Builder().baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create()).build()

            instance=retrofit.create(getdata::class.java)
        }

    }
}