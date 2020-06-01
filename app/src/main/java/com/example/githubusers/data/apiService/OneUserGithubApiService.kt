package com.example.githubusers.data.apiService

import com.example.githubusers.data.model.User
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface OneUserGithubApiService {
    @GET("/users/{username}")
    fun search(@Path("username") UserName: String): Observable<User>

    companion object Factory {
        fun create(): OneUserGithubApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build()

            return retrofit.create(OneUserGithubApiService::class.java);
        }
    }
}