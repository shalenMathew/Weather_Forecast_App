package com.example.weatherforecastapp.di

import com.example.weatherforecastapp.data.location.DefaultLocationTracker
import com.example.weatherforecastapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
abstract fun bindsLocationTracker(defaultLocationTracker: DefaultLocationTracker):LocationTracker

}