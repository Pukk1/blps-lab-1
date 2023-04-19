package com.iver.blpslab1.dao.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByUsername(username: String): Optional<UserEntity>
}
