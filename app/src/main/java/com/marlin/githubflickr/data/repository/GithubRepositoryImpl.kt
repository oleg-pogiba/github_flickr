package com.marlin.githubflickr.data.repository

import com.marlin.githubflickr.data.api.GitHubService
import com.marlin.githubflickr.data.model.User
import io.reactivex.Observable
import retrofit2.Call

class GithubRepositoryImpl(val gitHubService: GitHubService) : GithubRepository {
    override fun getRandomUser(): Observable<User> {
        return gitHubService.getUserList().map { t -> t[0] }
    }
}