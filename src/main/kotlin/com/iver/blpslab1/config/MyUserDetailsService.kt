package com.iver.blpslab1.config

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class MyUserDetailsService : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return if ("user" == username) {
            User.builder()
                .username("user")
                .password("xNNR06oFeJm") // password: password
                .roles("USER")
                .build()
        } else {
            throw UsernameNotFoundException("User not found")
        }
    }
}