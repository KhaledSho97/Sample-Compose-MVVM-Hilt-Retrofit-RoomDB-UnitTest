package com.khaled_sho.testmedicalapp.auth.di

import com.khaled_sho.testmedicalapp.auth.data.repository.AuthRepository
import com.khaled_sho.testmedicalapp.auth.data.repository.AuthRepositoryImpl
import com.khaled_sho.testmedicalapp.auth.data.source.local.AuthLocalDataSource
import com.khaled_sho.testmedicalapp.auth.data.source.local.AuthLocalDataSourceImpl
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCase
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCaseImpl
import com.khaled_sho.testmedicalapp.core.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class AuthModule {

    @Provides
    fun provideLocalDataSource(database: AppDatabase): AuthLocalDataSource =
        AuthLocalDataSourceImpl(database)

    @Provides
    fun provideRepository(
        localDataSource: AuthLocalDataSource
    ): AuthRepository = AuthRepositoryImpl(localDataSource)

    @Provides
    fun provideUseCase(repository: AuthRepository): AuthUseCase = AuthUseCaseImpl(repository)

}