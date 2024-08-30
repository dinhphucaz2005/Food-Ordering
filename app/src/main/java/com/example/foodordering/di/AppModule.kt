package com.example.foodordering.di

import android.app.Application
import android.content.Context
import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.data.remote.ApiServiceWithToken
import com.example.foodordering.data.repository.AuthRepositoryImpl
import com.example.foodordering.data.repository.CustomerRepositoryImpl
import com.example.foodordering.data.repository.ManagerRepositoryImpl
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.provider.UserProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideDatabase(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }

    @Provides
    @Singleton
    fun provideStorage(): StorageReference {
        return FirebaseStorage.getInstance().reference
    }

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService, userProvider: UserProvider): AuthRepository {
        return AuthRepositoryImpl(apiService, userProvider)
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(
        apiService: ApiService,
        apiServiceWithToken: ApiServiceWithToken
    ): CustomerRepository {
        return CustomerRepositoryImpl(apiService, apiServiceWithToken)
    }

    @Provides
    @Singleton
    fun provideManagerRepository(): ManagerRepository {
        return ManagerRepositoryImpl()
    }
}