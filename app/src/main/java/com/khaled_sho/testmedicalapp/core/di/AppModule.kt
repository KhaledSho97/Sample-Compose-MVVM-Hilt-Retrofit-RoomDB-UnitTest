package com.khaled_sho.testmedicalapp.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

}