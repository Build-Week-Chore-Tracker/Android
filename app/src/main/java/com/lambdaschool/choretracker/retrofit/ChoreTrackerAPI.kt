package com.lambdaschool.choretracker.retrofit

import com.google.gson.Gson
import com.lambdaschool.choretracker.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ChoreTrackerAPI {

    @POST("register")
    fun userRegistration(@Body registrationInfo: CredentialsAPI): Call<RegistrationReturnedAPI>

    @POST("login")
    fun userLogin(@Body loginInfo: CredentialsAPI): Call<LoginReturnedAPI>

    @GET("user/{id}")
    fun getFamilyMembersByParentId(@Path("id") parentId: Int): Call<List<ChildAPI>>

    @GET("user/1/chores")
    fun getAllChores(): Call<List<ChoreAPI>>

    @PUT("user/chores/{id}")
    fun updateChore(@Body chores: ChoreAPI, @Path("id") choreId: Int): Call<ChoreAPI>

    class Factory {

        companion object {
            val BASE_URL = "https://chore-tracker-app.herokuapp.com/api/auth/"
            val gson = Gson()
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
                level = HttpLoggingInterceptor.Level.BODY
            }


            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .retryOnConnectionFailure(false)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

            fun create(): ChoreTrackerAPI {

                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ChoreTrackerAPI::class.java)
            }
        }
    }

}