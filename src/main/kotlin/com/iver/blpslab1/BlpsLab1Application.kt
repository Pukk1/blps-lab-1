package com.iver.blpslab1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication(
    exclude = [
        SecurityAutoConfiguration::class
    ]
)
class BlpsLab1Application

fun main(args: Array<String>) {
//    println(BCryptPasswordEncoder().encode("xNNR06oFeJm"))
    runApplication<BlpsLab1Application>(*args)
}
