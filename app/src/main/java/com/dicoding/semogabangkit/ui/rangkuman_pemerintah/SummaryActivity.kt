package com.dicoding.semogabangkit.ui.rangkuman_pemerintah

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.cartesian.series.Column
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.databinding.ActivitySummaryBinding
import com.dicoding.semogabangkit.utils.Category
import com.dicoding.semogabangkit.viewmodel.ViewModelFactory


class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding
    private lateinit var viewModel:SummaryViewModel

    private var pembangunan = 0
    private var sosial = 0
    private var kesehatan = 0
    private var ekonomi = 0
    private var transportasi = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        showProgressBar()

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[SummaryViewModel::class.java]

        viewModel.getAllReports().observe(this, { reports ->
            countReportsByCategories(reports)
            hideProgressBar()
            showChart()
        })
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Aplikasi Untuk Pemerintah"
    }

    private fun showChart() {
        val anyChartView = binding.anyChartView

        val cartesian = AnyChart.column()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry(Category.PEMBANGUNAN, pembangunan))
        data.add(ValueDataEntry(Category.SOSIAL, sosial))
        data.add(ValueDataEntry(Category.KESEHATAN, kesehatan))
        data.add(ValueDataEntry(Category.EKONOMI, ekonomi))
        data.add(ValueDataEntry(Category.TRANPORTASI, transportasi))


        val column: Column = cartesian.column(data)

        column.fill("function() {" +
                "if (this.x == 'Pembangunan') return '#fff176';\n" +
                "if (this.x == 'Sosial') return '#81c784';\n" +
                "if (this.x == 'Kesehatan') return '#e57373';\n" +
                "if (this.x == 'Ekonomi') return '#64b5f6';\n" +
                "if (this.x == 'Transportasi') return '#ba68c8';\n" +
                "return 'green';}");

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0.0)
                .offsetY(5.0)
                .format("{%Value}{groupsSeparator:.}")

        cartesian.animation(true)
        cartesian.title("Jumlah Laporan Per Kategori")

        cartesian.yScale().minimum(0).ticks().allowFractional(false)

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator:.}")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

        cartesian.xAxis(0).title("Laporan").labels().fontSize(10)
        cartesian.yAxis(0).title("Banyak Laporan")

        anyChartView.setChart(cartesian)
    }

    private fun countReportsByCategories(reports: List<ReportEntity>) {
        pembangunan = reports.filter { it.tag == Category.PEMBANGUNAN}.size
        sosial = reports.filter { it.tag == Category.SOSIAL}.size
        kesehatan = reports.filter { it.tag == Category.KESEHATAN}.size
        ekonomi = reports.filter { it.tag == Category.EKONOMI}.size
        transportasi = reports.filter { it.tag == Category.TRANPORTASI}.size
    }

    fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}