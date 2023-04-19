package com.iver.blpslab1.dao.auth

import javax.persistence.*

//@Entity
//@Table(name = "users")
class UserEntity(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long,
    var username: String,
    var password: String,
    var roles: List<String>,
)