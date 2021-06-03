package com.dicoding.semogabangkit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.data.source.remote.RemoteDataSource
import com.dicoding.semogabangkit.data.source.remote.RemoteDataSource.*
import com.dicoding.semogabangkit.data.source.remote.response.ReportResponse
import com.dicoding.semogabangkit.data.source.remote.response.SuccessResponse

class BangkitRepository private constructor (private val remoteDataSource: RemoteDataSource) : BangkitDataSource {

    companion object {
        @Volatile
        private var instance: BangkitRepository? = null

        fun getInstance(remoteData: RemoteDataSource): BangkitRepository =
            instance ?: synchronized(this) {
                instance ?: BangkitRepository(remoteData).apply {
                    instance = this
                }
            }
    }

    override fun loadAllReports(): LiveData<List<ReportEntity>> {
        val reportResults = MutableLiveData<List<ReportEntity>>()

        remoteDataSource.loadAllReports(object : LoadAllReportsCallback {
            override fun onAllReportsReceived(reportResponses: List<ReportResponse>) {
                val responseList = ArrayList<ReportEntity>()
                for (response in reportResponses) {
                    responseList.add(convertReportResponseToEntity(response))
                }
                reportResults.postValue(responseList)
            }
        })
        return reportResults
    }

    override fun uploadReport(judul: String, deskripsi: String, encodedImage: String): LiveData<String> {
        val result = MutableLiveData<String>()

        remoteDataSource.uploadReport(object : UploadReportCallback {
            override fun onReportSent(successResponse: SuccessResponse) {
                result.postValue(successResponse.status)
            }
        }, judul, deskripsi, encodedImage)
        return result
    }

    private fun convertReportResponseToEntity(response: ReportResponse): ReportEntity {
        return ReportEntity(
            response.id,
            response.judul,
            response.tags,
            response.desc,
            response.image,
            response.votes,
            response.time
        )
    }

}