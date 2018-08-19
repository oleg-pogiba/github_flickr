package com.marlin.githubflickr.di

import com.marlin.githubflickr.data.api.FlickrService
import com.marlin.githubflickr.data.api.GitHubService
import com.marlin.githubflickr.data.repository.FlickrRepository
import com.marlin.githubflickr.data.repository.FlickrRepositoryImpl
import com.marlin.githubflickr.data.repository.GithubRepositoryImpl
import com.marlin.githubflickr.data.repository.GithubRepository
import com.marlin.githubflickr.presenter.MainPresenter
import com.marlin.githubflickr.ui.MainContract
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Koin module
val repositoryModule: Module = applicationContext {
    factory { MainPresenter(get(), get()) as MainContract.Presenter } // get() will resolve GithubRepository, FlickrRepository instance
    bean { GithubRepositoryImpl(get()) as GithubRepository }
    bean { FlickrRepositoryImpl(get()) as FlickrRepository }
}

val apiModule: Module = applicationContext {
    bean { provideGitHubService() }
    bean { provideFlickrService() }
}


fun provideGitHubService(): GitHubService {
    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    return retrofit.create(GitHubService::class.java)
}

fun provideFlickrService(): FlickrService {
    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    return retrofit.create(FlickrService::class.java)
}