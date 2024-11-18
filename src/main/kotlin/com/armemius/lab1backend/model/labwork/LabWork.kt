package com.armemius.lab1backend.model.labwork

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrePersist
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.ZonedDateTime

@Entity
class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "ID must be greater than 0")
    private val id = 0

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private val name: String? = null

    @Embedded
    @NotNull(message = "Coordinates cannot be null")
    private val coordinates: Coordinates? = null

    @Column(nullable = false, updatable = false)
    @NotNull(message = "Creation date cannot be null")
    private var creationDate: ZonedDateTime? = null

    @Column(nullable = false, length = 9046)
    @NotNull(message = "Description cannot be null")
    @Size(max = 9046, message = "Description must not be longer than 9046 characters")
    private val description: String? = null

    @Enumerated(EnumType.STRING)
    private val difficulty: Difficulty? = null

    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
    @NotNull(message = "Discipline cannot be null")
    private val discipline: Discipline? = null

    @Column(nullable = false)
    @NotNull(message = "Minimal point cannot be null")
    @Positive(message = "Minimal point must be greater than 0")
    private val minimalPoint: Int? = null

    @Column(nullable = false)
    @NotNull(message = "Personal qualities minimum cannot be null")
    @Positive(message = "Personal qualities minimum must be greater than 0")
    private val personalQualitiesMinimum: Int? = null

    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
    @NotNull(message = "Author cannot be null")
    private val author: Person? = null

    @PrePersist
    fun prePersist() {
        this.creationDate = ZonedDateTime.now()
    }
}
