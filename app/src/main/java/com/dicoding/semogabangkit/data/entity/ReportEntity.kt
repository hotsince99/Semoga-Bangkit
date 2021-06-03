package com.dicoding.semogabangkit.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportEntity(
    val id: Int,
    val title: String,
    val tag: String,
    val description: String,
    val imagePath: String,
    val upVote: Int,
    val time: String
) : Parcelable

/*val image: String? = null,
val location: String,
val votes: Int,
val id: Int,
val time: String,
val judul: String,
val tags: String*/
