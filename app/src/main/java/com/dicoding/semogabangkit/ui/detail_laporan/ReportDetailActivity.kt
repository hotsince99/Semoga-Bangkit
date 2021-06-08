package com.dicoding.semogabangkit.ui.detail_laporan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.databinding.ActivityReportDetailBinding
import com.dicoding.semogabangkit.utils.Category
import com.dicoding.semogabangkit.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ReportDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPORT = "extra_report"

        private var uniqueID: String? = null
        private const val PREF_UNIQUE_ID = "PREF_UNIQUE_ID"

        private lateinit var judul: String
        private lateinit var uuid: String
        private lateinit var reportId: String
        private var isUpvoted: Boolean = false
    }

    private lateinit var binding: ActivityReportDetailBinding
    private lateinit var viewModel: ReportDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val report = intent.getParcelableExtra<ReportEntity>(EXTRA_REPORT)
        judul = report?.title as String
        uuid = uuid(this@ReportDetailActivity) as String
        reportId = report.id.toString()

        isUpvoted = checkIsUpvoted(this@ReportDetailActivity, reportId)

        if (isUpvoted) {
            binding.fab.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
        } else {
            binding.fab.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
        }

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ReportDetailViewModel::class.java]

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Upvote", Toast.LENGTH_SHORT).show()
            if (isUpvoted) {
                cancelUpvote()
                showToast("Cancel Upvote")
                binding.fab.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
            } else {
                upvote()
                showToast("Upvoted")
                binding.fab.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
            }
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

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Detail Laporan"
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

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun upvote() {
        viewModel.upvoteReport(judul, uuid, true).observe(this, { response ->
            Log.d("report activity", response.toString())
        })

        setUpvote(this@ReportDetailActivity, reportId)
        isUpvoted = checkIsUpvoted(this@ReportDetailActivity, reportId)
    }

    private fun cancelUpvote() {
        viewModel.upvoteReport(judul, uuid, false).observe(this, { response ->
            Log.d("report activity", response.toString())

        })

        setCancelUpvote(this@ReportDetailActivity, reportId)
        isUpvoted = checkIsUpvoted(this@ReportDetailActivity, reportId)
    }

    @Synchronized
    private fun uuid(context: Context): String? {
        if (uniqueID == null) {
            val sharedPrefs: SharedPreferences = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE)
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null)
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString()
                val editor: SharedPreferences.Editor = sharedPrefs.edit()
                editor.putString(PREF_UNIQUE_ID, uniqueID)
                editor.apply()
            }
        }
        return uniqueID
    }

    @Synchronized
    private fun checkIsUpvoted(context: Context, reportId: String): Boolean {

        val sharedPrefs: SharedPreferences = context.getSharedPreferences(reportId, Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean(reportId, false)
    }

    @Synchronized
    private fun setUpvote(context: Context, reportId: String) {

        val sharedPrefs: SharedPreferences = context.getSharedPreferences(reportId, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        editor.putBoolean(reportId, true)
        editor.apply()

    }

    @Synchronized
    private fun setCancelUpvote(context: Context, reportId: String) {

        val sharedPrefs: SharedPreferences = context.getSharedPreferences(reportId, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        editor.putBoolean(reportId, false)
        editor.apply()

    }

}