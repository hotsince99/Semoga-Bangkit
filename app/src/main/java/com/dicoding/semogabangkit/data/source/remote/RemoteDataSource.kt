package com.dicoding.semogabangkit.data.source.remote

import android.util.Log
import com.dicoding.semogabangkit.data.source.remote.response.ReportResponse
import com.dicoding.semogabangkit.data.source.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private val TAG = this::class.java.simpleName

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource().apply { instance = this }
                }
    }

    fun loadAllReports(callback: LoadAllReportsCallback){

        val client = ApiConfig.getApiService().loadAllReports()
        client.enqueue(object : Callback<List<ReportResponse>> {
            override fun onResponse(call: Call<List<ReportResponse>>, response: Response<List<ReportResponse>>) {
                if (response.isSuccessful) {
                    callback.onAllReportsReceived(response.body()!!)
                    Log.d("JJ RDS", response.body()!![0].judul)
                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<List<ReportResponse>>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

        })

        //Log.d("JJ RDS", resultReport[0].judul)
        //Log.d("JJ RDS", resultReport.size.toString())

    }

    interface LoadAllReportsCallback {
        fun onAllReportsReceived(reportResponses: List<ReportResponse>)
    }
}
