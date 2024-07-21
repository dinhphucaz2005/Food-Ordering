package com.example.foodordering.di

import android.content.Context
import com.example.foodordering.data.repository.firebase.FAuthRepositoryImpl
import com.example.foodordering.data.repository.firebase.FCustomerRepositoryImpl
import com.example.foodordering.data.repository.firebase.FManagerRepositoryImpl
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.domain.repository.ManagerRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
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

    private val storage by lazy {
        FirebaseStorage.getInstance().reference
    }

    fun provideDatabase() = database

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return FAuthRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(): CustomerRepository {
        return FCustomerRepositoryImpl(provideDatabase())
    }

    fun provideManagerRepository(): ManagerRepository {
        return FManagerRepositoryImpl(provideDatabase(), provideStorage())
    }

    fun provideStorage() = storage

    private lateinit var applicationContext: Context

    fun setContext(context: Context) {
        applicationContext = context
    }

    fun provideContext(): Context = applicationContext

}
