package com.dicoding.semogabangkitsummary.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ReportResponse(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("votes")
    val votes: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("desc")
    val desc: String,

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("tags")
    val tags: String
)
