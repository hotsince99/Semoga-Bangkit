package com.dicoding.semogabangkit.ui.rangkuman_pemerintah

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.semogabangkit.data.BangkitRepository
import com.dicoding.semogabangkit.data.entity.ReportEntity

class SummaryViewModel(private val bangkitRepository: BangkitRepository) : ViewModel() {

    fun getAllReports(): LiveData<List<ReportEntity>> = bangkitRepository.loadAllReports()

}