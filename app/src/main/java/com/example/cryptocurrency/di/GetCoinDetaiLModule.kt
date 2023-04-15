package com.example.cryptocurrency.di

import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GetCoinDetaiLModule {


    @Provides
    fun provideUseCase(coinrepository: CoinRepository): GetCoinUseCase {
        return GetCoinUseCase(coinrepository)
    }
}