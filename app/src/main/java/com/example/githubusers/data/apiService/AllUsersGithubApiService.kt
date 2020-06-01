package com.example.githubusers.data.apiService

import com.example.githubusers.data.model.Users
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AllUsersGithubApiService {
    @GET("users")
    fun search(@Query("since") since: String): Observable<List<Users>>

    companion object Factory {
        fun create(): AllUsersGithubApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build()

            return retrofit.create(AllUsersGithubApiService::class.java);
        }
    }
}