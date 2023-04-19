package com.iver.blpslab1.security.jwt

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException

class JwtAuthenticationException : AuthenticationException {

    private var httpStatus: HttpStatus? = null

    constructor(msg: String?) : super(msg)
    constructor(msg: String?, httpStatus: HttpStatus?) : super(msg) {
        this.httpStatus = httpStatus
    }
}
