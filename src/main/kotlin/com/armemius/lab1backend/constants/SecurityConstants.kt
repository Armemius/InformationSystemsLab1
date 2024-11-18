package com.armemius.lab1backend.constants

import java.nio.charset.StandardCharsets
import java.security.Key
import javax.crypto.spec.SecretKeySpec

object SecurityConstants {
    const val JWT_EXPIRATION: Long = (24 * 60 * 60 * 1000).toLong()

    private val SECRET_KEY: String =
        System.getenv("JWT_SECRET_KEY")
            ?: throw IllegalArgumentException("JWT_SECRET_KEY environment variable not set")

    val JWT_SECRET: Key =
        SecretKeySpec(
            SECRET_KEY.toByteArray(StandardCharsets.UTF_8),
            "HmacSHA256",
        )
}
