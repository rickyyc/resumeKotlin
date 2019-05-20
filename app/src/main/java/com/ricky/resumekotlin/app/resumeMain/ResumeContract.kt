package com.ricky.resumekotlin.app.resumeMain

import com.ricky.resumekotlin.app.BasePresenter
import com.ricky.resumekotlin.app.BaseView
import com.ricky.resumekotlin.app.data.Experience

/**
 * This specifies the contract between the view and the presenter.
 */
interface ResumeContract {

    interface View : BaseView<Presenter> {

        fun showName(name: String)

        fun showSummary(summary: String)

        fun showExperience(experiences: List<Experience>)

        fun showError()
    }

    interface Presenter : BasePresenter {
        fun loadResume()
    }
}
