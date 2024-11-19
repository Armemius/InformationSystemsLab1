package com.armemius.lab1backend.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleJwtIncorrectException(e: Exception?): ResponseEntity<Map<String, String>> =
        ResponseEntity<Map<String, String>>(mapOf("error" to "Ti eblo kokosovoe"), HttpStatus.I_AM_A_TEAPOT)
}
