package com.dicoding.semogabangkit.ui.main_page

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.databinding.ActivityMainPageBinding
import com.dicoding.semogabangkit.ui.detail_laporan.ReportDetailActivity
import com.dicoding.semogabangkit.ui.formulir_keluhan.FormActivity
import com.dicoding.semogabangkit.ui.rangkuman_pemerintah.SummaryActivity
import com.dicoding.semogabangkit.viewmodel.ViewModelFactory

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding
    private lateinit var viewModel: MainPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        showProgressBar()

        binding.fab.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[MainPageViewModel::class.java]

        viewModel.getAllReports().observe(this, { reports ->
            showRecyclerCardView(reports)
            hideProgressBar()
        })
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Aplikasi Untuk Masyarakat"
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_summary_activity -> {
                val intent = Intent(this, SummaryActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }*/

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