package com.armemius.lab1backend.model.labwork

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Embeddable
class Location(
    @NotNull(message = "Description cannot be null")
    private val x: Double,
    @NotNull(message = "Y cannot be null")
    private val y: Float,
    @NotNull(message = "Z cannot be null")
    private val z: Int,
) : Serializable
