package com.armemius.lab1backend.dto

import lombok.Data

@Data
data class AuthResponseDTO(
    val token: String?,
    val tokenType: String? = "Bearer",
)
