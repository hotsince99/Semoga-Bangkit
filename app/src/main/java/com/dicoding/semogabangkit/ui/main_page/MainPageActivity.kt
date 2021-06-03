package com.dicoding.semogabangkit.ui.main_page

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.databinding.ActivityMainPageBinding
import com.dicoding.semogabangkit.ui.detail_laporan.ReportDetailActivity
import com.dicoding.semogabangkit.ui.formulir_keluhan.FormActivity
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

        binding.fab.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        viewModel.getAllReports().observe(this, { reports ->
            showRecyclerCardView(reports)
        })



    }

    private fun showRecyclerCardView(reportList: List<ReportEntity>) {
        binding.rvReports.layoutManager = LinearLayoutManager(this)
        val adapter = ReportListAdapter()
        adapter.setCourses(reportList)
        adapter.setCallback(object : ReportListAdapter.Callback {
            override fun onItemClick(item: ReportEntity) {
                showReportDetailActivity(item)
            }
        })
        binding.rvReports.adapter = adapter
    }

    private fun showReportDetailActivity(item: ReportEntity) {
        val intent = Intent(this, ReportDetailActivity::class.java)
        intent.putExtra(ReportDetailActivity.EXTRA_REPORT, item)
        startActivity(intent)
    }

    fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}