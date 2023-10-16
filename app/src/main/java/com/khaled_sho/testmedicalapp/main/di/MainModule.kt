package com.khaled_sho.testmedicalapp.main.di

import com.khaled_sho.testmedicalapp.main.data.mapper.ListOfDrugMapper
import com.khaled_sho.testmedicalapp.main.data.repository.MainRepository
import com.khaled_sho.testmedicalapp.main.data.repository.MainRepositoryImpl
import com.khaled_sho.testmedicalapp.main.data.source.api.MainApi
import com.khaled_sho.testmedicalapp.main.data.source.remote.MainRemoteDataSource
import com.khaled_sho.testmedicalapp.main.data.source.remote.MainRemoteDataSourceImpl
import com.khaled_sho.testmedicalapp.main.data.usecase.MainUseCase
import com.khaled_sho.testmedicalapp.main.data.usecase.MainUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class MainModule {

    @Provides
    fun provideRemoteDataSource(api: MainApi): MainRemoteDataSource = MainRemoteDataSourceImpl(api)

    @Provides
    fun provideRepository(
        remoteDataSource: MainRemoteDataSource
    ): MainRepository = MainRepositoryImpl(remoteDataSource, ListOfDrugMapper())

    @Provides
    fun provideUseCase(repository: MainRepository): MainUseCase = MainUseCaseImpl(repository)

}