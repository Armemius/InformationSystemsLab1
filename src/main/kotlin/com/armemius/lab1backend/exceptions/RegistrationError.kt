package com.armemius.lab1backend.exceptions

import jakarta.validation.ValidationException

class RegistrationError(
    message: String,
) : ValidationException(message)
