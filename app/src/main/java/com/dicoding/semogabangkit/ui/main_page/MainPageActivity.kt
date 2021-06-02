package com.dicoding.semogabangkit.ui.main_page

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.databinding.ActivityMainPageBinding
import com.dicoding.semogabangkit.utils.DummyReports
import com.dicoding.semogabangkit.viewmodel.ViewModelFactory

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        showProgressBar()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MainPageViewModel::class.java]

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Buat Laporan", Snackbar.LENGTH_LONG).show()
        }

        // val reportList: List<ReportEntity> = DummyReports.generate()

        viewModel.getAllReports().observe(this, { reports ->
            showRecyclerCardView(reports)
        })



    }

    private fun showRecyclerCardView(reportList: List<ReportEntity>) {
        binding.rvReports.layoutManager = LinearLayoutManager(this)
        val adapter = ReportListAdapter()
        adapter.setCourses(reportList)
        binding.rvReports.adapter = adapter
    }

    fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}