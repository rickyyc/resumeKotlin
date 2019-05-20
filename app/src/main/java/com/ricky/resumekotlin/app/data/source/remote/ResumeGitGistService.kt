package com.ricky.resumekotlin.app.data.source.remote

import com.ricky.resumekotlin.app.data.Resume
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ResumeGitGistService {
    @GET("9885ace71c245ad67b826d24c2cfa5bb/raw/e7eab7f102bdfb883b9aa1254ebe89acea128974/test_data.json")
    fun getResume(): Call<Resume>

    companion object {
        private val service = create()

        fun getService(): ResumeGitGistService {
            return service
        }

        private fun create(): ResumeGitGistService {
            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client: OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://gist.github.com/rickyyc/")
                    .client(client)
                    .build()

            return retrofit.create(ResumeGitGistService::class.java)
        }


    }

}