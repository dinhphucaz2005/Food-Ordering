package com.example.foodordering.di

import RetrofitClient
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.foodordering.data.repository.AuthRepositoryImpl
import com.example.foodordering.data.repository.CustomerRepositoryImpl
import com.example.foodordering.data.repository.FAuthRepositoryImpl
import com.example.foodordering.data.repository.ManagerRepositoryImpl
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.domain.repository.ManagerRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val database by lazy {
        FirebaseDatabase.getInstance().reference
    }

    fun provideDatabase() = database

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

    fun provideManagerRepository(): ManagerRepository {
        return ManagerRepositoryImpl()
    }
}
