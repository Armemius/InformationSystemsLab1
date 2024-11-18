package com.armemius.lab1backend.model.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length

@Entity
@Table(name = "Users")
class User(
    login: String,
    username: String,
    password: String,
    role: Roles = Roles.USER,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    @NotNull(message = "Login cannot be null")
    @Length(min = 5, max = 16, message = "Login should be between 5 and 16 characters")
    val login: String? = login

    @Column(nullable = false)
    @NotNull(message = "Username cannot be null")
    @Length(min = 5, max = 16, message = "Username should be between 5 and 16 characters")
    val username: String? = username

    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be empty")
    val password: String? = password

    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    @Enumerated(EnumType.STRING)
    val role: Roles? = role
}
