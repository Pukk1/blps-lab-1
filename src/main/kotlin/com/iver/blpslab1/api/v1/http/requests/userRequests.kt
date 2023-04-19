package com.iver.blpslab1.api.v1.http.requests

import com.iver.blpslab1.dao.user.Role
import com.iver.blpslab1.dao.user.UserEntity

data class UserRequest(
    val id: Long,
    val username: String,
    val password: String,
    val isActive: Boolean,
)

fun UserRequest.toEntity() : UserEntity {
    val entity = UserEntity(
        id = id,
        username = username,
        password = password,
        isActive = isActive,
    )
    entity.roles = mutableSetOf(Role.USER)
    return entity
}