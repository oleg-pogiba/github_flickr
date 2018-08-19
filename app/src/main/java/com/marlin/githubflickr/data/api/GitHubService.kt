package com.marlin.githubflickr.data.api

import com.marlin.githubflickr.data.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    fun getRepositoryList(@Path("user") user: String): Call<List<Repo>>
}