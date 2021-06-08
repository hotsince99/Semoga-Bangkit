package com.dicoding.semogabangkitsummary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.semogabangkitsummary.data.BangkitRepository
import com.dicoding.semogabangkitsummary.di.Injection
import com.dicoding.semogabangkitsummary.ui.detail_laporan.ReportDetailViewModel
import com.dicoding.semogabangkitsummary.ui.rangkuman_pemerintah.SummaryViewModel

class ViewModelFactory private constructor(private val bangkitRepository: BangkitRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(SummaryViewModel::class.java) -> {
                return SummaryViewModel(bangkitRepository) as T
            }
            modelClass.isAssignableFrom(ReportDetailViewModel::class.java) -> {
                return ReportDetailViewModel(bangkitRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}