package com.iver.blpslab1.service.user

import com.iver.blpslab1.api.v1.http.requests.UserRequest
import com.iver.blpslab1.api.v1.http.requests.toEntity
import com.iver.blpslab1.dao.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun addUser(userRequest: UserRequest) {
        userRepository.save(userRequest.toEntity())
    }
}