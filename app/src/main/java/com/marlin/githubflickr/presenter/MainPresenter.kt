package com.marlin.githubflickr.presenter

import android.provider.Contacts
import android.util.Log
import com.marlin.githubflickr.data.model.CombinedData
import com.marlin.githubflickr.data.model.User
import com.marlin.githubflickr.data.repository.FlickrRepository
import com.marlin.githubflickr.data.repository.GithubRepository
import com.marlin.githubflickr.data.response.Result
import com.marlin.githubflickr.ui.MainContract
import com.marlin.githubflickr.utils.log
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import timber.log.Timber
import kotlin.coroutines.experimental.CoroutineContext


class MainPresenter(
        val githubRepository: GithubRepository,
        val flickrRepository: FlickrRepository) : MainContract.Presenter {

    private val uiContext: CoroutineContext = Dispatchers.Main // for ui
    private val bgContext: CoroutineContext = Dispatchers.Default // for background from the common pool
    private var view: MainContract.View? = null
    private val rootJob = Job()
    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null

        rootJob.cancel()
    }

    override fun initData() {
        selectRandomUser()
    }

    private fun selectRandomUser() {
        GlobalScope.launch(bgContext + rootJob) {
            log("start")
            val result = githubRepository.getRandomUser()
            if (result is Result.Success) {
                loadRepoByUser(result.data.login)
            } else if (result is Result.Error) {
                result.exception.printStackTrace()
            }
        }
    }

    private fun loadRepoByUser(user: String?) {
        if (user != null){
            GlobalScope.launch(bgContext + rootJob) {
                val result = githubRepository.getRepoList(user)
                if (result is Result.Success) {
                    updateUi(result.data)
                } else if (result is Result.Error) {
                    result.exception.printStackTrace()
                }
            }
        }

    }

    private suspend fun updateUi(s: Any) {
        withContext(uiContext) {
            view!!.setContent(s.toString())
        }
    }
}
