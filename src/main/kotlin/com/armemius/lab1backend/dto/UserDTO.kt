package com.armemius.lab1backend.dto

data class UserDTO(
    val login: String,
    val username: String?,
    val password: String,
    val role: String?,
)
