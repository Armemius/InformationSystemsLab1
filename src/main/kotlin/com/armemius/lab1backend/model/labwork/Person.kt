package com.armemius.lab1backend.model.labwork

import jakarta.persistence.Column
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

@Entity
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private val name: String? = null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Eye color cannot be null")
    private val eyeColor: Color? = null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Hair color cannot be null")
    private val hairColor: Color? = null

    @Embedded
    private val location: Location? = null

    @Column(nullable = false)
    @NotNull(message = "Weight cannot be null")
    @Positive(message = "Weight must be greater than 0")
    private val weight: Float? = null

    @Enumerated(EnumType.STRING)
    private val nationality: Country? = null
}
