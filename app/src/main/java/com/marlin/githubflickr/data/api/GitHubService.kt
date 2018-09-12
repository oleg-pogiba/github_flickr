package com.marlin.githubflickr.data.api

import com.marlin.githubflickr.data.model.Repo
import com.marlin.githubflickr.data.model.User
import io.reactivex.Observable
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    fun getRepositoryList(@Path("user") user: String): Deferred<List<Repo>>

    @GET("users")
    fun getUserList(): Deferred<List<User>>
}