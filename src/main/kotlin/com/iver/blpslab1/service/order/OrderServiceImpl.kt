package com.iver.blpslab1.service.order

import com.iver.blpslab1.dao.order.entity.OrderEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import com.iver.blpslab1.dao.order.repository.OrderRepository
import com.iver.blpslab1.exception.NotFoundException
import com.iver.blpslab1.remote.retrofit2.payment.PayRequest
import com.iver.blpslab1.remote.retrofit2.payment.PaymentIntegration
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val paymentIntegration: PaymentIntegration,
) : OrderService {
    override fun createOrder(order: OrderEntity): OrderEntity =
        orderRepository.save(order)

    @Transactional
    override fun updateOrder(order: OrderEntity) {
        orderRepository.findById(order.id!!).orElseThrow { NotFoundException("Order not found") }
        orderRepository.save(order)
    }

    override fun getOrder(orderId: OrderId): OrderEntity? {
        return orderRepository.findById(orderId).orElse(null)
    }

    override fun deleteOrder(orderId: OrderId) {
        deleteOrder(orderId)
    }

    @Transactional
    override fun buyOrder(orderId: OrderId) {
        val order = getOrder(orderId) ?: throw NotFoundException("Order not found")
        paymentIntegration.pay(
            PayRequest(order.coast)
        )
    }
}
