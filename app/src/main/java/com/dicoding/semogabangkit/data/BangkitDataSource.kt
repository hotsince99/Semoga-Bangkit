package com.dicoding.semogabangkit.data

import androidx.lifecycle.LiveData
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.data.source.remote.response.ReportResponse

interface BangkitDataSource {

    fun loadAllReports(): LiveData<List<ReportEntity>>

}