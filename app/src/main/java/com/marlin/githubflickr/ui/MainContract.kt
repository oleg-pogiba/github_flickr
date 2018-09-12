package com.marlin.githubflickr.ui

import com.marlin.githubflickr.data.model.CombinedData

interface MainContract {
    interface Presenter {
        fun attachView(view: MainContract.View)
        fun detachView()
        fun initData()
    }

    interface View {
        fun setContent(content:String)
    }
}
