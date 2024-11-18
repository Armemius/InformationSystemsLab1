package com.armemius.lab1backend.repositories

import com.armemius.lab1backend.model.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, Long?> {
    fun existsByLogin(login: String?): Boolean

    fun findFirstByLogin(username: String): User?
}
