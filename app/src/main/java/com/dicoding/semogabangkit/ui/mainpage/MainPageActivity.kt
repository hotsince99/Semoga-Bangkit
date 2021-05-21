package com.dicoding.semogabangkit.ui.mainpage

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.data.ReportEntity
import com.dicoding.semogabangkit.databinding.ActivityMainPageBinding
import com.dicoding.semogabangkit.utils.DummyReports

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Buat Laporan", Snackbar.LENGTH_LONG).show()
        }

        showRecyclerCardView()

    }

    private fun showRecyclerCardView() {
        binding.rvReports.layoutManager = LinearLayoutManager(this)
        val adapter = ReportListAdapter(DummyReports.generate() as ArrayList<ReportEntity>)
        binding.rvReports.adapter = adapter
    }
}