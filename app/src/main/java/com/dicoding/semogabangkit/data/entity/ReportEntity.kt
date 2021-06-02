package com.dicoding.semogabangkit.data.entity

data class ReportEntity(
    val id: Int,
    val title: String,
    val tag: String,
    val description: String,
    val imagePath: String?,
    val upVote: Int,
    val location: String
)

/*val image: String? = null,
val location: String,
val votes: Int,
val id: Int,
val time: String,
val judul: String,
val tags: String*/
