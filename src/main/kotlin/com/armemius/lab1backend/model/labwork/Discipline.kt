package com.armemius.lab1backend.model.labwork

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero

@Entity
class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "ID must be greater than 0")
    private val id: Long? = null

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private var name: String? = null

    @Column(nullable = false)
    @NotNull(message = "Practice hours cannot be null")
    @PositiveOrZero(message = "Practice hours must be positive or zero")
    private var practiceHours: Int? = null

    @Column(nullable = false)
    @NotNull(message = "Practice hours cannot be null")
    @PositiveOrZero(message = "Practice hours must be positive or zero")
    private val selfStudyHours = 0
}
