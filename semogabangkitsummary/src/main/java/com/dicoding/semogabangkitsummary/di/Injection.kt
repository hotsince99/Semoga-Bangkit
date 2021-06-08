package com.dicoding.semogabangkitsummary.di

import com.dicoding.semogabangkitsummary.data.BangkitRepository
import com.dicoding.semogabangkitsummary.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): BangkitRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return BangkitRepository.getInstance(remoteDataSource)
    }
}