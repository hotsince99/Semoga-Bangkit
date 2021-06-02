package com.dicoding.semogabangkit.ui.main_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.semogabangkit.data.BangkitRepository
import com.dicoding.semogabangkit.data.entity.ReportEntity

class MainPageViewModel(private val bangkitRepository: BangkitRepository): ViewModel() {

    fun getAllReports(): LiveData<List<ReportEntity>> = bangkitRepository.loadAllReports()

}