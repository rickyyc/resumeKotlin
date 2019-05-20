package com.ricky.resumekotlin.app.resumeMain

import com.ricky.resumekotlin.app.data.Experience
import com.ricky.resumekotlin.app.data.Resume
import com.ricky.resumekotlin.app.data.source.ResumeDataSource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ResumePresenterTest {
    private var testData = object : ResumeDataSource {
        override fun getResume(callback: ResumeDataSource.ResumeDataCallback) {
            getResumeInvoked = true
        }
    }

    private val testView = object : ResumeContract.View {
        override lateinit var presenter: ResumeContract.Presenter

        override fun showName(name: String) {
            showNameInvoked = true
        }

        override fun showSummary(summary: String) {
            showSummaryInvoked = true
        }

        override fun showExperience(experiences: List<Experience>) {
            showExperienceInvoked = true
        }

        override fun showError() {
            showErrorInvoked = true
        }
    }

    private val testPresenter = ResumePresenter(testData, testView)


    var getResumeInvoked: Boolean = false

    var showNameInvoked: Boolean = false
    var showSummaryInvoked: Boolean = false
    var showExperienceInvoked: Boolean = false
    var showErrorInvoked: Boolean = false

    @Before
    fun setUp() {
        // testData
        getResumeInvoked = false

        // testView
        showNameInvoked = false
        showSummaryInvoked = false
        showExperienceInvoked = false
        showErrorInvoked = false
    }

    @Test
    fun start() {
        testPresenter.start()
        Assert.assertTrue(getResumeInvoked)
    }

    @Test
    fun loadResume() {
        testPresenter.loadResume()
        Assert.assertTrue(getResumeInvoked)
    }

    @Test
    fun onResumeDataLoaded() {
        testPresenter.onResumeDataLoaded(Resume())
        Assert.assertTrue(showNameInvoked)
        Assert.assertTrue(showSummaryInvoked)
        Assert.assertTrue(showExperienceInvoked)
    }

    @Test
    fun onDataNotAvailable() {
        testPresenter.onDataNotAvailable()
        Assert.assertTrue(showErrorInvoked)
    }
}