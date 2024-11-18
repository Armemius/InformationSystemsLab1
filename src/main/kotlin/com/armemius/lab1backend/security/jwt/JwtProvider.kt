package com.armemius.lab1backend.security.jwt

import com.armemius.lab1backend.constants.SecurityConstants
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider {
    fun generateToken(auth: Authentication): String {
        val username: String = auth.name
        val currentDate = Date()
        val expireDate = Date(currentDate.time + SecurityConstants.JWT_EXPIRATION)

        return Jwts
            .builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
            .compact()
    }

    fun getUsernameFromJwt(token: String?): String {
        val claims: Claims =
            Jwts
                .parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .body
        return claims.subject
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts
                .parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            throw AuthenticationCredentialsNotFoundException("JWT is incorrect")
        }
    }
}
