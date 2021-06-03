package com.dicoding.semogabangkit.ui.detail_laporan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.semogabangkit.data.BangkitRepository

class ReportDetailViewModel(private val bangkitRepository: BangkitRepository) : ViewModel() {

    fun upvoteReport(id: Int, uuid: String, vote: Boolean)
            : LiveData<String> = bangkitRepository.upvoteReport(id, uuid, vote)

}