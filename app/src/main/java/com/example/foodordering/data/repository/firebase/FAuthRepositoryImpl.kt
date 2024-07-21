package com.example.foodordering.data.repository.firebase

import com.example.foodordering.data.dto.UserDTO
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.util.AppResource
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FAuthRepositoryImpl : AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    override suspend fun login(email: String, password: String): AppResource<UserDTO> {
        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    val user = authResult.user
                    if (user != null) {
                        val userDTO = UserDTO(
                            name = user.displayName,
                            email = user.email,
                            phoneNumber = user.phoneNumber,
                            token = user.uid
                        )
                        continuation.resume(AppResource.Success(userDTO))
                    } else {
                        continuation.resume(AppResource.Error("User not found"))
                    }
                }
                .addOnFailureListener { exception ->
                    exception.message?.let { AppResource.Error(it) }
                        ?.let { continuation.resume(it) }
                }
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): AppResource<UserDTO> {
        return suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    val user = authResult.user
                    if (user != null) {
                        val userDTO = UserDTO(
                            name = name,
                            email = email,
                            phoneNumber = phoneNumber,
                            token = user.uid
                        )
                        continuation.resume(AppResource.Success(userDTO))
                    } else {
                        continuation.resume(AppResource.Error("User not found"))
                    }
                }
                .addOnFailureListener { exception ->
                    exception.message?.let { AppResource.Error(it) }
                        ?.let { continuation.resume(it) }
                }
        }
    }
}