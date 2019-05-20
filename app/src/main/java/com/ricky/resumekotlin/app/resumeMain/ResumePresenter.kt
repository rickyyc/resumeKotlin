package com.ricky.resumekotlin.app.resumeMain

import com.ricky.resumekotlin.app.data.Resume
import com.ricky.resumekotlin.app.data.source.ResumeDataSource

/**
 * Listens to user actions from the UI ([ResumeFragment]), retrieves the data and updates the
 * UI as required.
 */
class ResumePresenter(private val resumeRepository: ResumeDataSource, private val resumeView: ResumeContract.View)
    : ResumeContract.Presenter, ResumeDataSource.ResumeDataCallback {
    override fun start() {
        loadResume()
    }

    override fun loadResume() {
        resumeRepository.getResume(this)
    }

    override fun onResumeDataLoaded(resume: Resume) {
        this.resume = resume

        resumeView.showName(resume.name)
        resumeView.showSummary(resume.summary)
        resumeView.showExperience(resume.experiences)
    }

    override fun onDataNotAvailable() {
        resumeView.showError()
    }

    private lateinit var resume: Resume

    init {
        resumeView.presenter = this
    }
}
