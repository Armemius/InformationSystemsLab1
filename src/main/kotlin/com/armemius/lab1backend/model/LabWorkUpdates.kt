package com.armemius.lab1backend.model

import com.armemius.lab1backend.model.labwork.LabWork
import com.armemius.lab1backend.model.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrePersist
import jakarta.validation.constraints.NotNull
import java.time.ZonedDateTime

@Entity
class LabWorkUpdates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false, updatable = false)
    @NotNull(message = "Creation date cannot be null")
    private var updateTime: ZonedDateTime? = null

    @ManyToOne
    @NotNull(message = "Editor cannot be null")
    private val editor: User? = null

    @ManyToOne
    @NotNull(message = "LabWork cannot be null")
    private val labWork: LabWork? = null

    @PrePersist
    fun prePersist() {
        this.updateTime = ZonedDateTime.now()
    }
}
