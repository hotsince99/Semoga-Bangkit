package com.dicoding.semogabangkit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.databinding.ActivityMainBinding
import com.dicoding.semogabangkit.ui.mainpage.MainPageActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainPage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.main_page -> {
                val intent = Intent(this@MainActivity, MainPageActivity::class.java)
                startActivity(intent)
            }

            R.id.formulir_keluhan -> {

            }

            R.id.profil_pengguna -> {

            }

            R.id.detail_laporan -> {

            }

            R.id.laporan_untuk_pemerintah -> {

            }

            R.id.registrasi -> {

            }

            R.id.login -> {

            }
        }
    }
}