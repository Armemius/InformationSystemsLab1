package com.armemius.lab1backend.services

import com.armemius.lab1backend.dto.UserDTO
import com.armemius.lab1backend.model.user.Roles
import com.armemius.lab1backend.model.user.User
import com.armemius.lab1backend.repositories.UserRepository
import com.armemius.lab1backend.security.jwt.JwtProvider
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
        userDTO.username ?: throw RuntimeException("Invalid request parameters")
        if (userDTO.login.length < 5 ||
            16 < userDTO.login.length ||
            userDTO.username.length < 5 ||
            16 < userDTO.username.length ||
            userDTO.password.length < 8 ||
            255 < userDTO.password.length
        ) {
            throw RuntimeException("Invalid request parameters")
        }
        val role: Roles
        try {
            role = Roles.valueOf(userDTO.role ?: Roles.USER.name)
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("Invalid request parameters")
        }
        if (role == Roles.ADMIN && userRepository.existsByRole(Roles.ADMIN)) {
            val user =
                User(
                    login = userDTO.login,
                    username = userDTO.username,
                    password = passwordEncoder.encode(userDTO.password),
                    role = role,
                    active = false,
                )
            userRepository.save(user)
            throw RuntimeException("Account is created, but needs approval. Please contact admin")
        } else {
            val user =
                User(
                    login = userDTO.login,
                    username = userDTO.username,
                    password = passwordEncoder.encode(userDTO.password),
                    role = role,
                    active = true,
                )
            userRepository.save(user)
        }
    }

    fun getLoginFromHeader(authorizationHeader: String): String {
        val jwtToken = authorizationHeader.substring(7)
        return jwtProvider.getLoginFromJwt(jwtToken)
    }
}
