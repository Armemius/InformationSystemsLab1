package com.armemius.lab1backend.model.labwork

import com.armemius.lab1backend.model.LabWorkUpdates
import com.armemius.lab1backend.model.user.User
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
import jakarta.persistence.OneToMany
import jakarta.persistence.PrePersist
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.ZonedDateTime

@Entity
class LabWork(
    @Id
    @NotNull(message = "ID should not be null")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "ID must be greater than 0")
    private val id: Long? = null,
    @ManyToOne
    @NotNull(message = "Owner cannot be null")
    val owner: User,
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    val name: String,
    @Embedded
    @NotNull(message = "Coordinates cannot be null")
    val coordinates: Coordinates,
    @Column(updatable = false)
    @NotNull(message = "Creation date cannot be null")
    var creationDate: ZonedDateTime,
    @Column(length = 9046)
    @NotNull(message = "Description cannot be null")
    @Size(max = 9046, message = "Description must not be longer than 9046 characters")
    val description: String,
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Description cannot be null")
    val difficulty: Difficulty,
    @ManyToOne(optional = true, cascade = [CascadeType.ALL])
    val discipline: Discipline?,
    @NotNull(message = "Minimal point cannot be null")
    @Positive(message = "Minimal point must be greater than 0")
    val minimalPoint: Int,
    @NotNull(message = "Personal qualities minimum cannot be null")
    @Positive(message = "Personal qualities minimum must be greater than 0")
    val personalQualitiesMinimum: Int,
    @ManyToOne(optional = true, cascade = [CascadeType.ALL])
    val author: Person?,
    @OneToMany(mappedBy = "labWork", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    private val labWorkUpdates: MutableList<LabWorkUpdates> = mutableListOf(),
) {
    @PrePersist
    fun prePersist() {
        this.creationDate = ZonedDateTime.now()
    }
}
