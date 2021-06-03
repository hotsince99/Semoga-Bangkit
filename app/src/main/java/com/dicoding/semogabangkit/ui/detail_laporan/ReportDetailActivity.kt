package com.dicoding.semogabangkit.ui.detail_laporan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.databinding.ActivityReportDetailBinding
import com.dicoding.semogabangkit.utils.Category
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ReportDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPORT = "extra_report"
    }

    private lateinit var binding: ActivityReportDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // binding.toolbar.title = title

        val report = intent.getParcelableExtra<ReportEntity>(EXTRA_REPORT)

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Upvote", Toast.LENGTH_SHORT).show()
        }

        binding.apply {
            Glide.with(applicationContext)
                    .load(report?.imagePath)
                    .into(image)

            title.text = report?.title
            description.text = report?.description
            date.text = getDateTime(report?.time)

            category.text = report?.tag

            when (report?.tag) {
                Category.PEMBANGUNAN -> R.drawable.ic_tag_yellow
                Category.SOSIAL -> R.drawable.ic_tag_green
                Category.KESEHATAN -> R.drawable.ic_tag_red
                Category.EKONOMI -> R.drawable.ic_tag_blue
                Category.TRANPORTASI -> R.drawable.ic_tag_purple
                else -> R.drawable.ic_tag
            }.let { imgTag.setBackgroundResource(it) }

        }
    }

    private fun getDateTime(s: String?): String? {
        lateinit var dates: String
        try {
            val f: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
            val d: Date = f.parse(s)
            val date: DateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id"))
            val time: DateFormat = SimpleDateFormat("hh:mm a")
            dates = "${date.format(d)} - ${time.format(d)}"
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dates
    }
}