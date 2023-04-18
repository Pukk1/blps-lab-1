//package com.iver.blpslab1.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.invoke
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//class SecurityConfig {
//
//    @Bean
//    fun web(http: HttpSecurity): SecurityFilterChain {
//        http {
//            cors { disable() }
//            csrf { disable() }
//            authorizeHttpRequests {
//                authorize("/api/**", authenticated)
//                authorize(anyRequest, denyAll)
//            }
//        }
//
//        return http.build()
//    }
//}
