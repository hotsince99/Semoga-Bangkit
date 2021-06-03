package com.dicoding.semogabangkit.data.source.remote.retrofit

import com.dicoding.semogabangkit.data.source.remote.response.ReportResponse
import com.dicoding.semogabangkit.data.source.remote.response.SuccessResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("judul/?format=json")
    fun loadAllReports(): Call<List<ReportResponse>>

    @POST("upload/")
    @FormUrlEncoded
    fun uploadReport(
            @Field("judul") judul: String,
            @Field("desc") desc: String,
            @Field("image") encodedImage: String
    ): Call<SuccessResponse>

    @POST("upvote/")
    @FormUrlEncoded
    fun upvoteThisReport(
            @Field("judul") judul: String,
            @Field("uuid") uuid: String,
            @Field("vote") vote: Boolean
    ): Call<SuccessResponse>

    /*@GET("3/trending/movie/week?api_key=${BuildConfig.TMDB_API}")
    fun getListMovies(): Call<MovieListResponse>*/


    /*@GET("3/trending/tv/week?api_key=${BuildConfig.TMDB_API}")
    fun getListTv(): Call<TvListResponse>*/


    /*@GET("3/tv/{id}?api_key=${BuildConfig.TMDB_API}")
    fun getTvDetail(@Path("id") id: String): Call<TvDetailResponse>*/

}