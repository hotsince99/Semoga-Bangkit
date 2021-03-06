package com.dicoding.semogabangkit.data

import androidx.lifecycle.LiveData
import com.dicoding.semogabangkit.data.entity.ReportEntity

interface BangkitDataSource {

    fun loadAllReports(): LiveData<List<ReportEntity>>

    fun uploadReport(judul: String, deskripsi: String, encodedImage: String): LiveData<String>

    fun upvoteReport(judul: String, uuid: String, votes: Boolean): LiveData<String>

}