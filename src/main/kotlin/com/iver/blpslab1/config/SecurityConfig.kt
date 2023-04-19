package com.iver.blpslab1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

//    override fun configure(http: HttpSecurity) {
//        http
//            .authorizeRequests()
//            .antMatchers("/**").permitAll()
//            .and()
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
//    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()
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

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .authenticationProvider(jaasAuthenticationProvider())
            .userDetailsService(userDetailsService())
    }
}

//class CustomJaasAuthenticationProvider : JaasAuthenticationProvider() {
//    override fun authenticate(auth: Authentication?): Authentication {
//        return super.authenticate(auth)
//    }
//}
