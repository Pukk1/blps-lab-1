package com.iver.blpslab1.config

import com.sun.security.auth.UserPrincipal
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.io.IOException
import javax.security.auth.Subject
import javax.security.auth.callback.*
import javax.security.auth.login.LoginException
import javax.security.auth.spi.LoginModule


class CustomLoginModule(private val userDetailsService: UserDetailsService) : LoginModule {
    private var callbackHandler: CallbackHandler? = null
    private var subject: Subject? = null
    private var userPrincipal: UserPrincipal? = null
    override fun initialize(
        subject: Subject, callbackHandler: CallbackHandler, sharedState: Map<String?, *>?,
        options: Map<String?, *>?
    ) {
        this.subject = subject
        this.callbackHandler = callbackHandler
    }

    @Throws(LoginException::class)
    override fun login(): Boolean {
        val nameCallback = NameCallback("Username:")
        val passwordCallback = PasswordCallback("Password:", false)
        val callbacks: Array<Callback> = arrayOf(nameCallback, passwordCallback)
        try {
            callbackHandler!!.handle(callbacks)
        } catch (e: IOException) {
            throw LoginException("Error in callback: " + e.message)
        } catch (e: UnsupportedCallbackException) {
            throw LoginException("Error in callback: " + e.message)
        }
        val username = nameCallback.name
        val password: String = String(passwordCallback.password)
        val userDetails = userDetailsService.loadUserByUsername(username)
        if (passwordEncoder().matches(password, userDetails.password)) {
            userPrincipal = UserPrincipal(username)
            return true
        }
        throw BadCredentialsException("Invalid username/password")
    }

    @Throws(LoginException::class)
    override fun commit(): Boolean {
        subject!!.principals.add(userPrincipal)
        return true
    }

    @Throws(LoginException::class)
    override fun abort(): Boolean {
        return true
    }

    @Throws(LoginException::class)
    override fun logout(): Boolean {
        subject!!.principals.remove(userPrincipal)
        return true
    }

    private fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}