package com.velmurugan.inapploggerexample

import android.content.Context
import com.velmurugan.inapplogger.InAppLoggerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @GET("movielist.json")
    suspend fun getAllMovies(): Response<List<Movie>>

    @POST("movielist.json")
    suspend fun getAllMovies2(@Body name: Movie) : Response<List<Movie>>

    @GET("movielist.json/{name}")
    suspend fun getAllMoviesPath(@Path("name")name: String): Response<List<Movie>>

    @GET("movielist.json")
    suspend fun getAllMovieQuery(@Query("name") name: String, @Query("location") location: String): Response<List<Movie>>

    companion object {
        var retrofitService: ApiService? = null
        fun getInstance(context: Context): ApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .client(
                        OkHttpClient().newBuilder().addInterceptor(InAppLoggerInterceptor())
                            .build()
                    )
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }

    }
}