package com.iver.blpslab1.dao.user

import com.iver.blpslab1.security.UserDetails
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: CrudRepository<UserDetails, Long> {

    fun findByUserName(username: String): Optional<UserEntity>
}
