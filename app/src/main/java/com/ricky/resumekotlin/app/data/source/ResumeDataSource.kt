package com.ricky.resumekotlin.app.data.source

import com.ricky.resumekotlin.app.data.Resume

interface ResumeDataSource {
    interface ResumeDataCallback {
        fun onResumeDataLoaded(resume: Resume)
        fun onDataNotAvailable()
    }

    fun getResume(callback: ResumeDataCallback)
}