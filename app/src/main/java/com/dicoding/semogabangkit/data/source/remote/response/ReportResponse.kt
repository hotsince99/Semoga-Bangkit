package com.dicoding.semogabangkit.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ReportResponse(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("votes")
    val votes: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("deskripsi")
    val deskripsi: String? = null,

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("tags")
    val tags: String
)
