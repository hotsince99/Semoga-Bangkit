package com.dicoding.semogabangkit.data

data class ReportEntity(
    val id: Int,
    val title: String,
    val tag: Int,
    val description: String,
    val imagePath: String,
    val upVote: Int
)