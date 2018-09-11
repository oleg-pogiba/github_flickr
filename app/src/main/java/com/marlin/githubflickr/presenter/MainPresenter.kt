package com.marlin.githubflickr.presenter

import android.support.annotation.MainThread
import android.util.Log
import com.marlin.githubflickr.data.model.CombinedData
import com.marlin.githubflickr.data.model.User
import com.marlin.githubflickr.data.repository.FlickrRepository
import com.marlin.githubflickr.data.repository.GithubRepository
import com.marlin.githubflickr.ui.MainContract
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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

    override fun getCombinedList(): List<CombinedData> {
        var combinedData = mutableListOf<CombinedData>()
        githubRepository.getRandomUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: User? -> Log.d(">>>", t.toString())}

        return combinedData
    }
}
