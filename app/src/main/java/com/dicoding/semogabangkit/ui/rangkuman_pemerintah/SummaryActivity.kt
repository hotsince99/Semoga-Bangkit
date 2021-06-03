package com.dicoding.semogabangkit.ui.rangkuman_pemerintah

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.cartesian.series.Column
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.dicoding.semogabangkit.databinding.ActivitySummaryBinding
import com.dicoding.semogabangkit.utils.Category
import com.google.android.material.snackbar.Snackbar


class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val anyChartView = binding.anyChartView

        val cartesian = AnyChart.column()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry(Category.PEMBANGUNAN, 1000))
        data.add(ValueDataEntry(Category.SOSIAL, 2000))
        data.add(ValueDataEntry(Category.KESEHATAN, 3000))
        data.add(ValueDataEntry(Category.EKONOMI, 4000))
        data.add(ValueDataEntry(Category.TRANPORTASI, 5000))


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
        cartesian.title("Jumlah Upvote Laporan")

        cartesian.yScale().minimum(0.0)

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator:.}")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

        cartesian.xAxis(0).title("Laporan")
        cartesian.yAxis(0).title("Upvote")

        cartesian

        anyChartView.setChart(cartesian)
    }
}