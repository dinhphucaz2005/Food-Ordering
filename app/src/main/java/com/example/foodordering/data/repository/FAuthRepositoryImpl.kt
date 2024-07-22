package com.example.foodordering.data.repository

import com.example.foodordering.data.dto.UserDTO
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.util.AppResource
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FAuthRepositoryImpl : AuthRepository {

//    private val auth = FirebaseAuth.getInstance()


    override suspend fun login(email: String, password: String): AppResource<UserDTO> {
        TODO()
//        return suspendCoroutine { continuation ->
//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        val user = task.result?.user
//                        if (user != null) {
//                            val userDTO = UserDTO(
//                                user.uid,
//                                user.email,
//                                user.displayName,
//                                user.phoneNumber,
//                                user.photoUrl.toString()
//                            )
//                            continuation.resume(AppResource.Success(userDTO))
//                        } else {
//                            continuation.resume(AppResource.Error("User login failed: User is null"))
//                        }
//                    } else {
//                        val exception = task.exception
//                        if (exception != null) {
//                            continuation.resume(AppResource.Error("User login failed: ${exception.message}"))
//                        }
//                    }
//                }
//        }
    }

    override suspend fun register(
        name: String,
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ): AppResource<UserDTO> {
        TODO()
//        return suspendCoroutine { continuation ->
//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        val user = task.result?.user
//                        println("User ID: ${user?.uid}")
//                        if (user != null) {
//                            val userDTO = UserDTO(
//                                user.uid,
//                                user.email,
//                                user.displayName,
//                                user.phoneNumber,
//                                user.photoUrl.toString()
//                            )
//                            continuation.resume(AppResource.Success(userDTO))
//                        } else {
//                            continuation.resume(AppResource.Error("User registration failed: User is null"))
//                        }
//                    } else {
//                        val exception = task.exception
//                        if (exception != null) {
//                            continuation.resume(AppResource.Error("User registration failed: ${exception.message}"))
//                        } else {
//                            continuation.resume(AppResource.Error("User registration failed: Unknown error"))
//                        }
//                    }
//                }
//        }
    }
}