package com.iver.blpslab1.config


import com.iver.blpslab1.service.auth.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig(
    val userDetailsService: UserDetailsServiceImpl,
) {
    @Bean
    fun authenticationManager(
        http: HttpSecurity,
        userDetailService: UserDetailsServiceImpl
    ): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .userDetailsService(userDetailsService)
            .and()
            .build()
    }

//    @Bean
//    override fun authenticationManagerBean(): AuthenticationManager {
//        return super.authenticationManagerBean()
//    }

//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.authenticationProvider(jaasAuthProvider())
//        auth.userDetailsService(userDetailsService)
//    }
//
    @Bean
    fun jaasAuthProvider(): JaasAuthenticationProvider {
        val provider = JaasAuthenticationProvider()
        val resource: Resource =
            FileSystemResource("C:\\Users\\Ivan\\IdeaProjects\\blps-lab-1\\src\\main\\resources\\myapp.txt")
        provider.loginConfig = resource
        provider.setLoginContextName("myapp")
        return provider
    }

//    @Bean
//    fun web(http: HttpSecurity): SecurityFilterChain {
////        http.invoke {
////            cors { disable() }
////            csrf { disable() }
////            authorizeHttpRequests {
////                authorize("/api/**", authenticated)
////                authorize(anyRequest, denyAll)
////            }
////        }
//        http
//            .cors()
//            .disable()
//            .csrf()
//            .disable()
//            .authorizeRequests()
//            .antMatchers("/**")
//            .authenticated()
//            .and()
//            .httpBasic(Customizer.withDefaults())
//
//        return http.build()
//    }

    @Bean
    fun web(http: HttpSecurity): SecurityFilterChain {
        http
            .cors().disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/v1/**").authenticated()
            .anyRequest().authenticated()
            .and()
            .httpBasic()

        return http.build()
    }

//    override fun configure(http: HttpSecurity) {
//        http
//            .cors()
//            .disable()
//            .csrf()
//            .disable()
//            .authorizeRequests()
//            .antMatchers("/**")
//            .authenticated()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .httpBasic()
//    }
}