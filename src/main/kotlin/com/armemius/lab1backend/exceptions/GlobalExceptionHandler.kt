package com.armemius.lab1backend.exceptions

import jakarta.validation.ValidationException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleDtoExceptions(ex: HttpMessageNotReadableException): ResponseEntity<Map<String, String>> =
        ResponseEntity.badRequest().body(mapOf("error" to "Invalid DTO provided"))

    @ExceptionHandler(ValidationException::class)
    fun handleValidationExceptions(ex: ValidationException): ResponseEntity<Map<String, String>> =
        ResponseEntity.badRequest().body(mapOf("error" to ex.message!!))
}
