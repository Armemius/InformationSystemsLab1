package com.armemius.lab1backend.model.labwork

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Embeddable
class Coordinates(
    @NotNull(message = "X value should not be null")
    @DecimalMax(value = "327", message = "X coordinate must not be greater than 327")
    private val x: Float,
    @NotNull(message = "Y value should not be null")
    private val y: Long,
) : Serializable
