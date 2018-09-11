package com.marlin.githubflickr.data.repository

import com.marlin.githubflickr.data.model.User
import io.reactivex.Observable

public interface GithubRepository {
    fun getRandomUser(): Observable<User>
}