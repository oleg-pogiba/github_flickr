package com.marlin.githubflickr.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marlin.githubflickr.R
import com.marlin.githubflickr.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainContract.View {

    // Inject MainPresenter
    private val presenter: MainContract.Presenter  by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        greeting.setText("Hello world :-)")
        presenter.getCombinedList()
    }

    override fun onStop() {
        super.onStop()
    }
}
