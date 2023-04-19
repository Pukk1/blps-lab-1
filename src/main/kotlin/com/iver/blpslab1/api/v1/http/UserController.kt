package com.iver.blpslab1.api.v1.http

import com.iver.blpslab1.api.v1.http.requests.UserRequest
import com.iver.blpslab1.service.user.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody userRequest: UserRequest,
    ) {
        userService.addUser(userRequest)
    }
}