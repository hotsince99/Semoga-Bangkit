package com.dicoding.semogabangkit.ui

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.databinding.ActivityMainBinding
import com.dicoding.semogabangkit.ui.formulir_keluhan.FormActivity
import com.dicoding.semogabangkit.ui.main_page.MainPageActivity
import com.dicoding.semogabangkit.ui.rangkuman_pemerintah.SummaryActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainPage.setOnClickListener(this)
        binding.formulirKeluhan.setOnClickListener(this)
        binding.laporanUntukPemerintah.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.main_page -> {
                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
            }

            R.id.formulir_keluhan -> {
                val intent = Intent(this, FormActivity::class.java)
                startActivity(intent)
            }

            R.id.laporan_untuk_pemerintah -> {
                val intent = Intent(this, SummaryActivity::class.java)
                startActivity(intent)
            }
        }
    }


}