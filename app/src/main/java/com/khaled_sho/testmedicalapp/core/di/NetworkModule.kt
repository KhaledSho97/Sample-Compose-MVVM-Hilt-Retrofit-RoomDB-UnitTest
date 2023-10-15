package com.khaled_sho.testmedicalapp.core.di

import android.util.Log
import androidx.multidex.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        val BASE_URL = "https://run.mocky.io/"
        const val API_VERSION3 = "v3/"
    }

    private fun provideNetworkIntercepted(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val builder: Request.Builder =
                chain.request().newBuilder().addHeader("Accept", "application/json")
            val request: Request = builder.build()
            chain.proceed(request)
        }
    }

    private fun getOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor =
            HttpLoggingInterceptor { message -> Log.e("OkHttp", message) }

        loggingInterceptor.setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )

        return builder.readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()


    @Provides
    fun getRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL + API_VERSION3)
            .client(getOkHttpClient(provideNetworkIntercepted())).build()
    }
}