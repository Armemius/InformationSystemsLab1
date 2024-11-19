package com.armemius.lab1backend.dto

data class UserInfoDTO(
    val id: Long,
    val login: String,
    val username: String,
    val role: String,
    val active: Boolean,
)
