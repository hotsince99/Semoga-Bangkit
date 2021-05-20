package com.dicoding.semogabangkit.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.databinding.ActivityMainPageBinding

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Buat Laporan", Snackbar.LENGTH_LONG)
                    .setAction("Buat Laporan", null).show()
        }
    }
}