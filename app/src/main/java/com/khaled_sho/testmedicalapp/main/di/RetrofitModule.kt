package com.khaled_sho.testmedicalapp.main.di


import com.khaled_sho.testmedicalapp.main.data.source.api.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class RetrofitModule {

    @Provides
    fun provideApi(retrofit: Retrofit): MainApi = retrofit.create(
        MainApi::class.java
    )
}