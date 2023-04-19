package com.iver.blpslab1.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetails(
    private val username: String,
    private val password: String,
    private val authorities: List<GrantedAuthority>,
    private val isActive: Boolean,

    ) : UserDetails {
    override fun getAuthorities(): List<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return isActive
    }

    override fun isAccountNonLocked(): Boolean {
        return isActive
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isActive
    }

    override fun isEnabled(): Boolean {
        return isActive
    }
}
