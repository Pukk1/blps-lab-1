package com.iver.blpslab1.config

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus

@Configuration
class FilterConfig(
    @Value("\${X_EDX_API_KEY}")
    val secretKey: String
): Filter {

    @Bean
    fun secretKeyFilter(): FilterRegistrationBean<FilterConfig> {
        val registrationBean = FilterRegistrationBean<FilterConfig>()

        registrationBean.filter = FilterConfig(secretKey)
        registrationBean.addUrlPatterns("/api/v1/item/*")
        registrationBean.order = 2

        return registrationBean
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = (request as HttpServletRequest)
        val requestSecretKey = httpServletRequest.getHeader("X_EDX_API_KEY")
        if (requestSecretKey == secretKey) {
            chain?.doFilter(request, response)
        } else {
            val httpServletResponse = (response as HttpServletResponse)
            httpServletResponse.status = HttpStatus.UNAUTHORIZED.value()
            httpServletResponse.writer.write("Authentication fail")
        }
    }
}
