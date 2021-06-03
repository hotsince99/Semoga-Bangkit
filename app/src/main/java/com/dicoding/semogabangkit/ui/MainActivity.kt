package com.dicoding.semogabangkit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.databinding.ActivityMainBinding
import com.dicoding.semogabangkit.ui.detail_laporan.ReportDetailActivity
import com.dicoding.semogabangkit.ui.formulir_keluhan.FormActivity
import com.dicoding.semogabangkit.ui.main_page.MainPageActivity
import com.dicoding.semogabangkit.ui.profile_pengguna.ProfileActivity
import com.dicoding.semogabangkit.ui.rangkuman_pemerintah.SummaryActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainPage.setOnClickListener(this)
        binding.formulirKeluhan.setOnClickListener(this)
        binding.profilPengguna.setOnClickListener(this)
        binding.detailLaporan.setOnClickListener(this)
        binding.laporanUntukPemerintah.setOnClickListener(this)
        binding.registrasi.setOnClickListener(this)
        binding.login.setOnClickListener(this)
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

            R.id.profil_pengguna -> {
                /*val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra(ReportDetailActivity.EXTRA_ID, 1)
                startActivity(intent)*/
            }

            R.id.detail_laporan -> {
                /*val intent = Intent(this, ReportDetailActivity::class.java)
                intent.putExtra(ReportDetailActivity.EXTRA_ID, 1)
                startActivity(intent)*/
            }

            R.id.laporan_untuk_pemerintah -> {
                val intent = Intent(this, SummaryActivity::class.java)
                startActivity(intent)
            }

            R.id.registrasi -> {

            }

            R.id.login -> {

            }
        }
    }
}