package com.armemius.lab1backend.model.labwork

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.ZonedDateTime

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    val name: String?,
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Eye color cannot be null")
    val eyeColor: Color,
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Hair color cannot be null")
    val hairColor: Color,
    @Embedded
    val location: Location?,
    val birthday: ZonedDateTime?,
    @NotNull(message = "Weight cannot be null")
    @Positive(message = "Weight must be greater than 0")
    val weight: Float,
)
