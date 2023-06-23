//package com.iver.blpslab1.config
//
//import com.iver.blpslab1.dao.user.Role
//import com.iver.blpslab1.dao.user.UserRepository
//import com.iver.blpslab1.security.UserDetailsServiceImpl
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.io.ClassPathResource
//import org.springframework.security.authentication.jaas.JaasAuthenticationProvider
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig(
//    private val userRepository: UserRepository,
//) : WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity) {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/api/**").authenticated()/*.hasAnyAuthority(Role.ADMIN.name)*/
//            .and()
//            .formLogin()
//            .and()
//            .httpBasic()
//    }
//
//    @Bean
//    fun jaasAuthenticationProvider(): JaasAuthenticationProvider {
//        val provider = JaasAuthenticationProvider()
//        provider.loginConfig = ClassPathResource("jaas.config")
//        provider.setLoginContextName("Sample")
//        return provider
//    }
//
//    @Bean
//    fun myUserDetailsService(): UserDetailsServiceImpl {
//        return UserDetailsServiceImpl(userRepository)
//    }
//
//    @Bean
//    fun encoder(): PasswordEncoder? {
//        return BCryptPasswordEncoder()
//    }
//
//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth
//            .authenticationProvider(jaasAuthenticationProvider())
//            .userDetailsService(myUserDetailsService())
//    }
//}
