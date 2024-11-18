package com.armemius.lab1backend.services

import com.armemius.lab1backend.dto.UserDTO
import com.armemius.lab1backend.model.user.User
import com.armemius.lab1backend.repositories.UserRepository
import com.armemius.lab1backend.security.jwt.JwtProvider
import jakarta.validation.ValidationException
import lombok.RequiredArgsConstructor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
) {
    fun register(userDTO: UserDTO) {
        if (userDTO.login.length < 5 ||
            16 < userDTO.login.length ||
            userDTO.username.length < 5 ||
            16 < userDTO.username.length ||
            userDTO.password.length < 8 ||
            255 < userDTO.password.length
        ) {
            throw ValidationException("Invalid request body")
        }
        val user = User(userDTO.login, userDTO.username, passwordEncoder.encode(userDTO.password))
        userRepository.save(user)
    }

    fun getUsernameFromHeader(authorizationHeader: String): String {
        val jwtToken = authorizationHeader.substring(7)
        return jwtProvider.getUsernameFromJwt(jwtToken)
    }
}
