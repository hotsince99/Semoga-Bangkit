package com.dicoding.semogabangkitsummary.data.source.remote

import android.util.Log
import com.dicoding.semogabangkitsummary.data.source.remote.response.ReportResponse
import com.dicoding.semogabangkitsummary.data.source.remote.response.SuccessResponse
import com.dicoding.semogabangkitsummary.data.source.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

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
    }

    fun uploadReport(callback: UploadReportCallback, judul: String, deskripsi: String, encodedImage: String) {

        val client = ApiConfig.getApiService().uploadReport(judul, deskripsi, encodedImage)
        client.enqueue(object : Callback<SuccessResponse> {
            override fun onResponse(
                call: Call<SuccessResponse>,
                response: Response<SuccessResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onReportSent(response.body()!!)
                    Log.d(TAG, response.message())
                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<SuccessResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

        })

        return
    }

    fun upvoteReport(callback: UpvoteReportCallback, id: Int, uuid: String, votes: Boolean) {

        val client = ApiConfig.getApiService().upvoteThisReport(id, uuid, votes)
        client.enqueue(object : Callback<SuccessResponse> {
            override fun onResponse(call: Call<SuccessResponse>, response: Response<SuccessResponse>) {
                if (response.isSuccessful) {
                    callback.onUpvoteSent(response.body()!!)
                    Log.d(TAG, response.message())
                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<SuccessResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

        })
    }

    interface LoadAllReportsCallback {
        fun onAllReportsReceived(reportResponses: List<ReportResponse>)
    }

    interface UploadReportCallback {
        fun onReportSent(successResponse: SuccessResponse)
    }

    interface UpvoteReportCallback {
        fun onUpvoteSent(successResponse: SuccessResponse)
    }
}
