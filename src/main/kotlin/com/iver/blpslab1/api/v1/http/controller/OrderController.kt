package com.iver.blpslab1.api.v1.http.controller

import com.iver.blpslab1.api.v1.http.dto.OrderDto
import com.iver.blpslab1.dao.order.entity.OrderEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import com.iver.blpslab1.domain.service.order.OrderService
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/order")
class OrderController(
    private val orderService: OrderService,
    private val mapper: ModelMapper
) {
    @PostMapping()
    fun createOrder(
        @RequestBody orderDto: OrderDto,
    ): OrderDto {
        return mapper.map(
            orderService.createOrder(mapper.map(orderDto, OrderEntity::class.java)),
            OrderDto::class.java
        )
    }

    @GetMapping("/{orderId}")
    fun getItemById(
        @PathVariable orderId: OrderId,
    ) = orderService.getOrder(orderId)

    @PutMapping()
    fun updateItem(
        @PathVariable orderDto: OrderDto,
    ): OrderDto {
        return mapper.map(
            orderService.updateOrder(mapper.map(orderDto, OrderEntity::class.java)),
            OrderDto::class.java
        )
    }

    @DeleteMapping("/{orderId}")
    fun deleteItem(
        @PathVariable orderId: OrderId,
    ) = orderService.deleteOrder(orderId)
}