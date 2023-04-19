package com.iver.blpslab1.service.auth

import com.iver.blpslab1.dao.auth.UserEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserDetailsServiceImpl : UserDetailsService {
    private val users = listOf(
        UserEntity("admin", "password", mutableListOf("ADMIN", "USER")),
        UserEntity("user", "password", listOf("USER"))
    )

    override fun loadUserByUsername(username: String): UserDetails {
        val userOptional = users.stream()
            .filter { u: UserEntity -> u.username == username }
            .findFirst()
        if (userOptional.isEmpty) {
            throw UsernameNotFoundException("User not found")
        }
        val user = userOptional.get()
        val authorities = user.roles.stream()
            .map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
            .collect(Collectors.toList())
        return User(
            user.username, user.password, authorities
        )
    }
}