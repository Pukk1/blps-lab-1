//package com.iver.blpslab1.config
//
//import org.springframework.security.authentication.jaas.JaasAuthenticationCallbackHandler
//import org.springframework.security.core.Authentication
//import org.springframework.stereotype.Component
//import java.io.IOException
//import javax.security.auth.callback.*
//
//@Component
//class CustomCallbackHandler : JaasAuthenticationCallbackHandler {
//    @Throws(IOException::class, UnsupportedCallbackException::class)
//    override fun handle(callbacks: Array<Callback>) {
//        for (callback in callbacks) {
//            when (callback) {
//                is NameCallback -> {
//                    callback.name = "myusername"
//                }
//
//                is PasswordCallback -> {
//                    callback.password = "mypassword".toCharArray()
//                }
//
//                else -> {
//                    throw UnsupportedCallbackException(callback)
//                }
//            }
//        }
//    }
//
//    override fun handle(callback: Callback?, auth: Authentication?) {
//        TODO("Not yet implemented")
//    }
//}