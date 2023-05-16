package com.iver.blpslab1.remote.payment.log

import com.iver.blpslab1.dao.order.OrderId
import com.iver.blpslab1.remote.payment.PayRequest
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.ListenableFuture

interface PayLogIntegration {
    fun sendPayLog(key: OrderId, payRequest: PayRequest): ListenableFuture<SendResult<Long, String>>
}