package com.iver.blpslab1.domain.service.order

import com.iver.blpslab1.dao.order.entity.OrderEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import com.iver.blpslab1.dao.order.repository.OrderRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
) : OrderService {
    override fun createOrder(order: OrderEntity): OrderEntity =
        orderRepository.save(order)

    @Transactional
    override fun updateOrder(order: OrderEntity) {
        orderRepository.findById(order.id).orElseThrow { throw EntityNotFoundException() }
        orderRepository.save(order)
    }

    override fun getOrder(orderId: OrderId): OrderEntity? {
        return orderRepository.findById(orderId).orElse(null)
    }

    override fun deleteOrder(orderId: OrderId) {
        deleteOrder(orderId)
    }
}