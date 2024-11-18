package com.armemius.lab1backend.model.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @Length(min = 5, max = 16, message = "Name should be between 5 and 16 characters")
    private val name: String? = null

    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    @Length(min = 8, max = 255, message = "Password should be between 5 and 255 characters")
    private val password: String? = null

    @Enumerated(EnumType.STRING)
    private val role: Roles? = null
}
