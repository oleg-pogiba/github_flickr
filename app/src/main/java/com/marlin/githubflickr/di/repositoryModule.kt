package com.marlin.githubflickr.di

import com.marlin.githubflickr.data.repository.FlickrRepository
import com.marlin.githubflickr.data.repository.FlickrRepositoryImpl
import com.marlin.githubflickr.data.repository.GithubRepositoryImpl
import com.marlin.githubflickr.data.repository.GithubRepository
import com.marlin.githubflickr.presenter.MainPresenter
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext


// Koin module
val repositoryModule : Module = applicationContext {
    factory { MainPresenter(get(), get()) } // get() will resolve GithubRepository, FlickrRepository instance
    bean { GithubRepositoryImpl() as GithubRepository }
    bean { FlickrRepositoryImpl() as FlickrRepository }
}