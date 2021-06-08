package com.dicoding.semogabangkit.ui.detail_laporan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.semogabangkit.data.BangkitRepository

class ReportDetailViewModel(private val bangkitRepository: BangkitRepository) : ViewModel() {

    fun upvoteReport(judul: String, uuid: String, vote: Boolean)
            : LiveData<String> = bangkitRepository.upvoteReport(judul, uuid, vote)

}