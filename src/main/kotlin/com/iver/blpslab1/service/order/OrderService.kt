package com.iver.blpslab1.service.order

import com.iver.blpslab1.dao.order.entity.OrderEntity
import com.iver.blpslab1.dao.order.entity.OrderId

interface OrderService {
    fun createOrder(order: OrderEntity): OrderEntity
    fun updateOrder(order: OrderEntity): OrderEntity
    fun getOrder(orderId: OrderId): OrderEntity?
    fun deleteOrder(orderId: OrderId)
    fun buyOrder(orderId: OrderId): String
}
