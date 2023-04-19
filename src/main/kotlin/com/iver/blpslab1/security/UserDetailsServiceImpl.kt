package com.iver.blpslab1.security

import com.iver.blpslab1.dao.user.UserEntity
import com.iver.blpslab1.dao.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import com.iver.blpslab1.dao.user.toSecurityModel
import java.util.*

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return findByUsername(username).get().toSecurityModel()
    }

    private fun findByUsername(username: String): Optional<UserEntity> =
        userRepository.findByUsername(username)
}