package com.marlin.githubflickr.ui

interface MainContract {
    interface Presenter {
        fun attachView(view: MainContract.View)
        fun detachView()
    }

    interface View {

    }
}
