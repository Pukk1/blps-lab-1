package com.iver.blpslab1.service.order

import com.iver.blpslab1.dao.order.OrderEntity
import com.iver.blpslab1.dao.order.OrderId

interface OrderService {
    fun createOrder(order: OrderEntity): OrderEntity
    fun updateOrder(order: OrderEntity): OrderEntity
    fun getOrder(orderId: OrderId): OrderEntity?
    fun deleteOrder(orderId: OrderId)
    fun buyOrder(orderId: OrderId): String
}
