package com.iver.blpslab1.config


import com.iver.blpslab1.service.auth.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.jaas.JaasAuthenticationCallbackHandler
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.security.auth.spi.LoginModule


@Configuration
@EnableWebSecurity
class SecurityConfig(
    val userDetailsService: UserDetailsServiceImpl,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .userDetailsService(userDetailsService)
            .httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(jaasAuthenticationProvider())
    }

    @Bean
    fun jaasAuthenticationProvider(): JaasAuthenticationProvider? {
        val jaasAuthProvider = JaasAuthenticationProvider()
        jaasAuthProvider.loginConfig = ClassPathResource("myapp.txt")
        return jaasAuthProvider
    }

    @Bean
    fun customLoginModule(): LoginModule? {
        return CustomLoginModule(userDetailsService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}