package com.armemius.lab1backend.controllers

import com.armemius.lab1backend.dto.AuthResponseDTO
import com.armemius.lab1backend.dto.UserDTO
import com.armemius.lab1backend.repositories.UserRepository
import com.armemius.lab1backend.security.jwt.JwtProvider
import com.armemius.lab1backend.services.AuthService
import jakarta.validation.Valid
import jakarta.validation.ValidationException
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = ["*"])
@RequiredArgsConstructor
class AuthController(
    private val userRepository: UserRepository,
    private val authService: AuthService,
    private val authManager: AuthenticationManager,
    private val jwtProvider: JwtProvider,
) {
    @PostMapping("/login")
    @CrossOrigin(value = ["*"])
    @Throws(ValidationException::class)
    fun login(
        @RequestBody userDTO: @Valid UserDTO?,
    ): ResponseEntity<*> {
        val authentication =
            authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    userDTO?.login,
                    userDTO?.password,
                ),
            )
        authentication ?: throw ValidationException("Unable to authenticate user")
        SecurityContextHolder.getContext().authentication = authentication
        val token: String = jwtProvider.generateToken(authentication)
        return ResponseEntity.ok(
            AuthResponseDTO(token),
        )
    }

    @PostMapping("/register")
    @CrossOrigin(value = ["*"])
    @Throws(ValidationException::class)
    fun register(
        @RequestBody userDTO: @Valid UserDTO?,
    ): ResponseEntity<*> {
        userDTO ?: throw ValidationException("Invalid request body")
        if (userRepository.existsByLogin(userDTO.login)) {
            throw ValidationException("Username taken")
        }
        authService.register(userDTO)
        val authentication =
            authManager
                .authenticate(
                    UsernamePasswordAuthenticationToken(
                        userDTO.login,
                        userDTO.password,
                    ),
                )
        authentication ?: throw ValidationException("Unable to authenticate user")
        SecurityContextHolder.getContext().authentication = authentication
        val token: String = jwtProvider.generateToken(authentication)
        return ResponseEntity.ok<Any>(AuthResponseDTO(token))
    }

//    @GetMapping("/username")
//    fun getUsernameFromToken(
//        @RequestHeader("Authorization") authorizationHeader: String?
//    ): ResponseEntity<*> {
//        authorizationHeader ?: throw AuthenticationError("Authorization header is missing")
//        return ResponseEntity.ok(authService.getUsernameFromHeader(authorizationHeader))
//    }
}
