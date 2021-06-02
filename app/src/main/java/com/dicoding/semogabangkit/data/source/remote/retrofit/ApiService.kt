package com.dicoding.semogabangkit.data.source.remote.retrofit

import com.dicoding.semogabangkit.data.source.remote.response.ReportResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("judul/?format=json")
    fun loadAllReports(): Call<List<ReportResponse>>

    /*@GET("3/trending/movie/week?api_key=${BuildConfig.TMDB_API}")
    fun getListMovies(): Call<MovieListResponse>*/


    /*@GET("3/trending/tv/week?api_key=${BuildConfig.TMDB_API}")
    fun getListTv(): Call<TvListResponse>*/


    /*@GET("3/tv/{id}?api_key=${BuildConfig.TMDB_API}")
    fun getTvDetail(@Path("id") id: String): Call<TvDetailResponse>*/

}