package com.example.kotlindemo


import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    fun getListRepos(@Path("user") user: String): Deferred<List<Repo>>
}