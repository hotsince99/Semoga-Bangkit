package com.dicoding.semogabangkitsummary.ui.rangkuman_pemerintah

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.semogabangkitsummary.data.BangkitRepository
import com.dicoding.semogabangkitsummary.data.entity.ReportEntity

class SummaryViewModel(private val bangkitRepository: BangkitRepository) : ViewModel() {

    fun getAllReports(): LiveData<List<ReportEntity>> = bangkitRepository.loadAllReports()

}