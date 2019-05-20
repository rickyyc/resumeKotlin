package com.ricky.resumekotlin.app.resumeMain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ricky.resumekotlin.app.BuildConfig
import com.ricky.resumekotlin.app.R
import com.ricky.resumekotlin.app.data.source.ResumeDataSource
import com.ricky.resumekotlin.app.data.source.local.MockResumeRepository
import com.ricky.resumekotlin.app.data.source.remote.RemoteResumeRepository
import com.ricky.resumekotlin.app.util.replaceFragmentInActivity

class ResumeActivity : AppCompatActivity() {

    private lateinit var resumePresenter: ResumePresenter
    private lateinit var resumeDataSource: ResumeDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resume_act)

        val resumeFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as ResumeFragment? ?: ResumeFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        // Create the presenter
        if (BuildConfig.BUILD_TYPE.contains("Mock")) {
            resumeDataSource = MockResumeRepository()
        } else {
            resumeDataSource = RemoteResumeRepository()
        }

        resumePresenter = ResumePresenter(resumeDataSource, resumeFragment)
    }

    override fun onResume() {
        super.onResume()
        resumePresenter.start()
    }
}
