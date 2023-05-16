package com.iver.blpslab1.remote.payment.log

import com.fasterxml.jackson.databind.ObjectMapper
import com.iver.blpslab1.dao.order.OrderId
import com.iver.blpslab1.remote.payment.PayRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFuture
import java.util.*

@Service
class PayLogIntegrationImpl(
    private val kafkaTemplate: KafkaTemplate<Long, String>,
    @Value("\${spring.kafka.pay-log.topic.name}")
    private val topicName: String,
    private val objectMapper: ObjectMapper,
) : PayLogIntegration {
    override fun sendPayLog(key: OrderId, payRequest: PayRequest): ListenableFuture<SendResult<Long, String>> {
        val data = objectMapper.writeValueAsString(payRequest)
        return kafkaTemplate.send(topicName, key, data)
    }
}