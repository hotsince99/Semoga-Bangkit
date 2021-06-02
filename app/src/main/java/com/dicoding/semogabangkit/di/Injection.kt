package com.dicoding.semogabangkit.di

import com.dicoding.semogabangkit.data.BangkitRepository
import com.dicoding.semogabangkit.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): BangkitRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return BangkitRepository.getInstance(remoteDataSource)
    }
}