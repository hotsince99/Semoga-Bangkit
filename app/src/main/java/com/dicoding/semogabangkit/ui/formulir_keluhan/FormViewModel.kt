package com.dicoding.semogabangkit.ui.formulir_keluhan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.semogabangkit.data.BangkitRepository
import com.dicoding.semogabangkit.data.entity.ReportEntity

class FormViewModel(private val bangkitRepository: BangkitRepository) : ViewModel() {

    fun uploadReport(judul: String, deskripsi: String, encodedImage: String)
        : LiveData<String> = bangkitRepository.uploadReport(judul, deskripsi, encodedImage)

}