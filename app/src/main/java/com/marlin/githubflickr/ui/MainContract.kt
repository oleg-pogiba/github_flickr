package com.marlin.githubflickr.ui

import com.marlin.githubflickr.data.model.CombinedData

interface MainContract {
    interface Presenter {
        fun attachView(view: MainContract.View)
        fun detachView()
        fun getCombinedList(): List<CombinedData>
    }

    interface View {

    }
}
