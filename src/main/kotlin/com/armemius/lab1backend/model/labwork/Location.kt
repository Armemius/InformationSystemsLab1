package com.armemius.lab1backend.model.labwork

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Embeddable
class Location : Serializable {
    @Column(nullable = false)
    @NotNull(message = "Description cannot be null")
    private val x: Double? = null

    @Column(nullable = false)
    @NotNull(message = "Y cannot be null")
    private val y: Float? = null

    @Column(nullable = false)
    @NotNull(message = "Z cannot be null")
    private val z: Int? = null
}
