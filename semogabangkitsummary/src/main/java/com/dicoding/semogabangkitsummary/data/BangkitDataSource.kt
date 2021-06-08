package com.dicoding.semogabangkitsummary.data

import androidx.lifecycle.LiveData
import com.dicoding.semogabangkitsummary.data.entity.ReportEntity

interface BangkitDataSource {

    fun loadAllReports(): LiveData<List<ReportEntity>>

    fun uploadReport(judul: String, deskripsi: String, encodedImage: String): LiveData<String>

    fun upvoteReport(id: Int, uuid: String, votes: Boolean): LiveData<String>

}