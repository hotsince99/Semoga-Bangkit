package com.dicoding.semogabangkit.ui.detail_laporan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.databinding.ActivityReportDetailBinding

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

            category.text = report?.tag

            when (report?.tag) {
                "Pembangunan" -> R.drawable.ic_tag_yellow
                "Sosial" -> R.drawable.ic_tag_green
                "Kesehatan" -> R.drawable.ic_tag_red
                "Ekonomi" -> R.drawable.ic_tag_blue
                "Transportasi" -> R.drawable.ic_tag_purple
                else -> R.drawable.ic_tag
            }.let { imgTag.setBackgroundResource(it) }

        }
    }
}