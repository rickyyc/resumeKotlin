package com.ricky.resumekotlin.app.data.source.remote

import com.ricky.resumekotlin.app.data.Resume
import com.ricky.resumekotlin.app.data.source.ResumeDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteResumeRepository : ResumeDataSource {
    override fun getResume(callback: ResumeDataSource.ResumeDataCallback) {
        val call = ResumeGitGistService.getService().getResume()
        call.enqueue(object : Callback<Resume> {
            override fun onFailure(call: Call<Resume>, t: Throwable) {
                callback.onDataNotAvailable()
            }

            override fun onResponse(call: Call<Resume>, response: Response<Resume>) {
                response.body()?.let {
                    callback.onResumeDataLoaded(it)
                }
            }
        })

    }
}