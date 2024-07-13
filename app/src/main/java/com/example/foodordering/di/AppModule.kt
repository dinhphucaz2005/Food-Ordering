package com.example.foodordering.di

import RetrofitClient
import com.example.foodordering.data.repository.AuthRepositoryImpl
import com.example.foodordering.data.repository.CustomerRepositoryImpl
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.domain.repository.CustomerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(RetrofitClient.getApiService())
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(): CustomerRepository {
        return CustomerRepositoryImpl(RetrofitClient.getApiServiceWithToken())
    }
}
