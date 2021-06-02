package com.dicoding.semogabangkit.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("Response")
	val response: List<ResponseItem?>? = null
)

data class ResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("votes")
	val votes: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("tags")
	val tags: String? = null
)
