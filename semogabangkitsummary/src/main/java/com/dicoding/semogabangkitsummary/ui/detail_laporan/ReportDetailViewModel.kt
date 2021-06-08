package com.dicoding.semogabangkitsummary.ui.detail_laporan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.semogabangkitsummary.data.BangkitRepository

class ReportDetailViewModel(private val bangkitRepository: BangkitRepository) : ViewModel() {

    fun upvoteReport(id: Int, uuid: String, vote: Boolean)
            : LiveData<String> = bangkitRepository.upvoteReport(id, uuid, vote)

}