package com.example.weatherforecastapp.di

import com.example.weatherforecastapp.data.repository.WeatherRepositoryImpl
import com.example.weatherforecastapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

 @Binds
 @Singleton
 abstract fun bindsWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl):WeatherRepository

}