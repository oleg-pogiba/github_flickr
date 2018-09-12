package com.marlin.githubflickr.data.repository

import com.marlin.githubflickr.data.model.Repo
import com.marlin.githubflickr.data.model.User
import com.marlin.githubflickr.data.response.Result

public interface GithubRepository {
    suspend fun getRandomUser(): Result<User>
    suspend fun getRepoList(user:String): Result<List<Repo>>
}