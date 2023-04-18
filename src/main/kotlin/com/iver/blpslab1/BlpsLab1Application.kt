package com.iver.blpslab1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        SecurityAutoConfiguration::class
    ]
)
class BlpsLab1Application

fun main(args: Array<String>) {
    runApplication<BlpsLab1Application>(*args)
}
