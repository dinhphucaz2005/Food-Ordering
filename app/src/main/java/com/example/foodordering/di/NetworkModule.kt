package com.example.foodordering.di

import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.data.remote.ApiServiceWithToken
import com.example.foodordering.provider.UserProvider
import com.example.foodordering.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val gsonConverterFactory by lazy {
        GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideApiServiceWithoutToken(): ApiService {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(gsonConverterFactory).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceWithToken(okHttpClient: OkHttpClient): ApiServiceWithToken {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient).build()
            .create(ApiServiceWithToken::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClientWithToken(userProvider: UserProvider): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer ${userProvider.getToken()}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }
}