package com.charlsgod.weatherapp.di

import android.content.Context
import com.charlsgod.weatherapp.data.api.ApiService
import com.charlsgod.weatherapp.data.local.LocalData
import com.charlsgod.weatherapp.data.local.LocalService
import com.charlsgod.weatherapp.utils.Constants
import com.charlsgod.weatherapp.utils.InternetHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideInternetHelper(@ApplicationContext appContext: Context): InternetHelper {
        return InternetHelper(context = appContext)
    }

    @Provides
    fun provideOkHttpNetworkInterceptor(): Interceptor {

        return Interceptor { chain ->

            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", Constants.API_KEY)
                .addQueryParameter("format", "json")
                .build()

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }


    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(
        okHttpLogger: HttpLoggingInterceptor,
        okHttpNetworkInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpLogger)
            .addInterceptor(okHttpNetworkInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideLocalService(): LocalService {
        return LocalData()
    }

}