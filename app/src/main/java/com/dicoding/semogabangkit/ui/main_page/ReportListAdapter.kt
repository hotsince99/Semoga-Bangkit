package com.dicoding.semogabangkit.ui.main_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.data.entity.ReportEntity
import com.dicoding.semogabangkit.databinding.ItemCardviewReportBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ReportListAdapter() : RecyclerView.Adapter<ReportListAdapter.ViewHolder>() {

    private var listReports = ArrayList<ReportEntity>()

    fun setCourses(courses: List<ReportEntity>?) {
        if (courses == null) return
        this.listReports.clear()
        this.listReports.addAll(courses)
    }

    interface Callback {
        fun onItemClick(item: ReportEntity)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    inner class ViewHolder (private val binding: ItemCardviewReportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportEntity) {
            binding.apply {
                tvTitle.text = report.title
                tvCategory.text = report.tag
                tvDate.text = getDateTime(report.time)

                when (report.tag) {
                    "Pembangunan" -> R.drawable.ic_tag_yellow
                    "Sosial" -> R.drawable.ic_tag_green
                    "Kesehatan" -> R.drawable.ic_tag_red
                    "Ekonomi" -> R.drawable.ic_tag_blue
                    "Transportasi" -> R.drawable.ic_tag_purple
                    else -> R.drawable.ic_tag
                }.let { imgTag.setBackgroundResource(it) }

                tvDescription.text = report.description

                btnSetFavorite.text = when (report.upVote) {
                    0 -> "0 Upvote"
                    1 -> "1 Upvote"
                    else -> "${report.upVote} Upvotes"
                }

                Glide.with(itemView.context)
                    .load(report.imagePath)
                    .into(imgPhoto)

                root.setOnClickListener {
                    callback?.onItemClick(report)
                }

            }
        }
    }

    private fun getDateTime(s: String?): String? {
        lateinit var dates: String
        try {
            val f: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
            val d: Date = f.parse(s)
            val date: DateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id"))
            val time: DateFormat = SimpleDateFormat("hh:mm a")
            dates = "${time.format(d)} - ${date.format(d)}"
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dates
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listReports[position])
    }

    override fun getItemCount(): Int {
        return listReports.size
    }


}