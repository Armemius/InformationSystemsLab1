package com.armemius.lab1backend.services

import com.armemius.lab1backend.model.user.Roles
import com.armemius.lab1backend.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findFirstByLogin(username) ?: throw UsernameNotFoundException("User not found")
        return User(user.login, user.password, setAuthorities())
    }

    companion object {
        private val ROLE = Roles.USER.toString()

        private fun setAuthorities(): List<GrantedAuthority> {
            val authorities: MutableList<GrantedAuthority> = ArrayList()

            authorities.add(SimpleGrantedAuthority(ROLE))
            return authorities
        }
    }
}
