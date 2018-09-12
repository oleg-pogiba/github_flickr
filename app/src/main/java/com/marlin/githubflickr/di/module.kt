package com.marlin.githubflickr.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


// Koin module
val repositoryModule: Module = applicationContext {
    factory { MainPresenter(get(), get()) as MainContract.Presenter } // get() will resolve GithubRepository, FlickrRepository instance
    bean { GithubRepositoryImpl(get()) as GithubRepository }
    bean { FlickrRepositoryImpl(get()) as FlickrRepository }
    bean { provideOkHttpClient()}
}

val apiModule: Module = applicationContext {
    bean { provideGitHubService(get()) }
    bean { provideFlickrService(get()) }
}


fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}


fun provideGitHubService(client: OkHttpClient): GitHubService {
    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    return retrofit.create(GitHubService::class.java)
}

fun provideFlickrService(client: OkHttpClient): FlickrService {
    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    return retrofit.create(FlickrService::class.java)
}