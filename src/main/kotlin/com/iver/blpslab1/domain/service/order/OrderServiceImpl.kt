package com.iver.blpslab1.domain.service.order

import com.iver.blpslab1.dao.order.entity.OrderEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import com.iver.blpslab1.dao.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
) : OrderService {
    override fun createOrder(order: OrderEntity): OrderEntity =
        orderRepository.save(order)

    override fun updateOrder(order: OrderEntity) {
        orderRepository.save(order)
    }

    override fun getOrder(orderId: OrderId): OrderEntity? {
        return orderRepository.findById(orderId).orElse(null)
    }

    override fun deleteOrder(orderId: OrderId) {
        deleteOrder(orderId)
    }
}