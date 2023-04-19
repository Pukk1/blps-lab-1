package com.iver.blpslab1.config

import com.iver.blpslab1.dao.user.UserRepository
import com.iver.blpslab1.security.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userRepository: UserRepository,
) : WebSecurityConfigurerAdapter() {

//    override fun configure(http: HttpSecurity) {
//        http
//            .authorizeRequests()
//            .antMatchers("/**").permitAll()
//            .and()
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
//    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/**").authenticated()
            .and()
            .formLogin()
            .and()
            .httpBasic()
    }

    @Bean
    fun jaasAuthenticationProvider(): JaasAuthenticationProvider {
        val provider = JaasAuthenticationProvider()
        provider.loginConfig = ClassPathResource("jaas.config")
        provider.setLoginContextName("Sample")
        return provider
    }

    @Bean
    fun myUserDetailsService(): UserDetailsServiceImpl {
        return UserDetailsServiceImpl(userRepository)
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .authenticationProvider(jaasAuthenticationProvider())
            .userDetailsService(myUserDetailsService())
    }
}

//class MyUserDetailsService : UserDetailsService {
//    override fun loadUserByUsername(username: String): UserDetails {
//        return if ("user" == username) {
//            User.builder()
//                .username("user")
//                .password("\$2a\$10\$mbWxyHyT0hCkhp7ME4tE3.rBsUdVYQFrReMULu9KQLxbrtBvrIz0K") // password: password
//                .roles("USER")
//                .build()
//        } else {
//            throw UsernameNotFoundException("User not found")
//        }
//    }
//}