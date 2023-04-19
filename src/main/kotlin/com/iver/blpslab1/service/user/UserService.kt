package com.iver.blpslab1.service.user

import com.iver.blpslab1.api.v1.http.requests.UserRequest
import org.springframework.stereotype.Service

interface UserService {
    fun addUser(userRequest: UserRequest)
}