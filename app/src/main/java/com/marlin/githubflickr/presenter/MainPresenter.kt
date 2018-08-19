package com.marlin.githubflickr.presenter

import com.marlin.githubflickr.data.repository.FlickrRepository
import com.marlin.githubflickr.data.repository.GithubRepository
import com.marlin.githubflickr.ui.MainContract

class MainPresenter(
        val githubRepository: GithubRepository,
        val flickrRepository: FlickrRepository) : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

}
