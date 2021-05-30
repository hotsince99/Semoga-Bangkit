package com.dicoding.semogabangkit.ui.main_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.data.ReportEntity
import com.dicoding.semogabangkit.databinding.ItemCardviewReportBinding

class ReportListAdapter(private val reports: ArrayList<ReportEntity>) : RecyclerView.Adapter<ReportListAdapter.ViewHolder>() {

    inner class ViewHolder (private val binding: ItemCardviewReportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportEntity) {
            binding.apply {
                tvTitle.text = report.title
                tvCategory.text = when (report.tag) {
                    1 -> "Pembangunan"
                    2 -> "Sosial"
                    3 -> "Kesehatan"
                    4 -> "Ekonomi"
                    5 -> "Transportasi"
                    else -> "no tag"
                }

                when (report.tag) {
                    1 -> R.drawable.ic_tag_yellow
                    2 -> R.drawable.ic_tag_green
                    3 -> R.drawable.ic_tag_red
                    4 -> R.drawable.ic_tag_blue
                    5 -> R.drawable.ic_tag_purple
                    else -> R.drawable.ic_tag
                }.let { imgTag.setBackgroundResource(it) }

                tvDescription.text = report.description

                btnSetFavorite.text = when (report.upVote) {
                    1 -> "1 Upvote"
                    else -> "${report.upVote} Upvotes"
                }

                Glide.with(itemView.context)
                    .load(report.imagePath.toInt())
                    .into(imgPhoto)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reports[position])
    }

    override fun getItemCount(): Int {
        return reports.size
    }


}