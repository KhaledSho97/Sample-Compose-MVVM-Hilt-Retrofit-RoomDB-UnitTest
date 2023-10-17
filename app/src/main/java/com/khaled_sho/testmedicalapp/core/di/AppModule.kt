package com.khaled_sho.testmedicalapp.core.di

import android.app.Application
import android.content.Context
import com.khaled_sho.testmedicalapp.core.util.coroutines.AppDispatcherProvider
import com.khaled_sho.testmedicalapp.core.util.coroutines.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

}