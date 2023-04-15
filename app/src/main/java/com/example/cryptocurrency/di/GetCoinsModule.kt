package com.example.cryptocurrency.di

import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetCoinsModule {

    @Provides
    fun provideUseCase(coinrepository:CoinRepository): GetCoinsUseCase{
        return GetCoinsUseCase(coinrepository)
    }
}