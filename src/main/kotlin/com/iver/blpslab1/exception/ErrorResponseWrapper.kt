package com.iver.blpslab1.exception

enum class HTTPErrorResponseStatus {
    NOT_FOUND,
}

class ErrorResponseWrapper(
    val status: HTTPErrorResponseStatus,
    val description: String
)
