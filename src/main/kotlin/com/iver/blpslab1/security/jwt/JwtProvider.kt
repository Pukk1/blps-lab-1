package com.iver.blpslab1.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class JwtProvider(
    @Value("\${jwt.secret}")
    private var secretKey: String,
    @Value("\${jwt.header}")
    private val authHeader: String,
    @Value("\${jwt.expirationTime}")
    private val expirationTimeDays: Int,

    private val userDetailsService: UserDetailsService
) {

    @PostConstruct
    private fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun generateToken(username: String, role: String): String {
        val claims = hashMapOf<String, Any>()
        claims["role"] = role
        val context = SecurityContextHolder.getContext()
        return generateToken(claims, username)

    }

    private fun generateToken(claims: Map<String, Any>, username: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + daysToMillis(expirationTimeDays)))
            .signWith(SignatureAlgorithm.HS512, secretKey).compact()
    }

    fun validateToken(token: String): Boolean =
        try {
            val claimJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            claimJws.body.expiration.after(Date())
        } catch (e: Exception) {
            throw JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED)
        }



    private fun daysToMillis(days: Int) =
        days * 24 * 60 * 60 * 1000
}
