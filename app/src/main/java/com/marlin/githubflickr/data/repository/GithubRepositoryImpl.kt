package com.marlin.githubflickr.data.repository

import com.marlin.githubflickr.data.api.GitHubService
import com.marlin.githubflickr.data.model.Repo
import com.marlin.githubflickr.data.model.User
import com.marlin.githubflickr.data.response.Result
import com.marlin.githubflickr.utils.log
import kotlinx.coroutines.experimental.delay
import retrofit2.HttpException

class GithubRepositoryImpl(val gitHubService: GitHubService) : GithubRepository {
    override suspend fun getRandomUser(): Result<User> {
        return try {
            val response = gitHubService.getUserList()
            val result = response.await()
            log("where am I?")
            Result.Success(result[0])
        } catch (e: HttpException) {
            // Catch http errors
            Result.Error(e)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    override suspend fun getRepoList(user:String): Result<List<Repo>> {
        return try {
            val response = gitHubService.getRepositoryList(user)
            val result = response.await()
            Result.Success(result)
        } catch (e: HttpException) {
            // Catch http errors
            Result.Error(e)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}