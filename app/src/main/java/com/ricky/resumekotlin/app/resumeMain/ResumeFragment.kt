package com.ricky.resumekotlin.app.resumeMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ricky.resumekotlin.app.R
import com.ricky.resumekotlin.app.data.Experience
import com.ricky.resumekotlin.app.data.Resume
import com.ricky.resumekotlin.app.util.showSnackBar

/**
 * Display the [Resume]s. User can choose to view all, active or completed tasks.
 */
class ResumeFragment : Fragment(), ResumeContract.View {
    override lateinit var presenter: ResumeContract.Presenter

    private lateinit var nameTextView: TextView
    private lateinit var summaryTextView: TextView
    private lateinit var experienceLayout: LinearLayout


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.resume_frag, container, false)

        setHasOptionsMenu(true)

        nameTextView = root.findViewById(R.id.name)
        summaryTextView = root.findViewById(R.id.summary)
        experienceLayout = root.findViewById(R.id.experienceList)

        return root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        }
        return true
    }

    override fun showName(name: String) {
        nameTextView.text = name
    }

    override fun showSummary(summary: String) {
        summaryTextView.text = summary
    }

    override fun showExperience(experiences: List<Experience>) {
        experienceLayout.removeAllViews()

        for (experience in experiences) {
            val current = LayoutInflater.from(context).inflate(R.layout.experience_view, null)

            current?.let {
                (it.findViewById(R.id.company) as TextView).text = experience.company
                (it.findViewById(R.id.role) as TextView).text = experience.role
                (it.findViewById(R.id.description) as TextView).text = experience.description
                (it.findViewById(R.id.from) as TextView).text = experience.from
                (it.findViewById(R.id.to) as TextView).text = experience.to

                experienceLayout.addView(current, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
            }
        }
    }

    override fun showError() {
        view?.showSnackBar("Error", Snackbar.LENGTH_LONG)
    }

    companion object {
        fun newInstance() = ResumeFragment()
    }

}
