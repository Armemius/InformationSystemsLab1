package com.armemius.lab1backend.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
@CrossOrigin(value = ["*"])
class TestController {
    @GetMapping
    fun get(): String = "Pipiska"
}
