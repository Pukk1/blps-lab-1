package com.iver.blpslab1.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig {
    private val mapper = ModelMapper()

    @Bean
    fun getModelMapper(): ModelMapper? {
        return mapper
    }
}