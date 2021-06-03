package com.dicoding.semogabangkit.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SuccessResponse(
    @SerializedName("status")
    val status: String
)