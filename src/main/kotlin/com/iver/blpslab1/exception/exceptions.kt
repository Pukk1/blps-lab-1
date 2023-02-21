package com.iver.blpslab1.exception

class NotFoundException(msg: String) : RuntimeException(msg) {
    constructor() : this("")
}