package com.dicoding.semogabangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.semogabangkit.data.BangkitRepository
import com.dicoding.semogabangkit.di.Injection
import com.dicoding.semogabangkit.ui.detail_laporan.ReportDetailViewModel
import com.dicoding.semogabangkit.ui.formulir_keluhan.FormViewModel
import com.dicoding.semogabangkit.ui.main_page.MainPageViewModel
import com.dicoding.semogabangkit.ui.rangkuman_pemerintah.SummaryViewModel

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
            modelClass.isAssignableFrom(MainPageViewModel::class.java) -> {
                return MainPageViewModel(bangkitRepository) as T
            }
            modelClass.isAssignableFrom(FormViewModel::class.java) -> {
                return FormViewModel(bangkitRepository) as T
            }
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