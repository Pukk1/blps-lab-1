package com.iver.blpslab1.api.v1.http.controller

import com.iver.blpslab1.api.v1.http.dto.RequestOrderDto
import com.iver.blpslab1.api.v1.http.dto.ResponseOrderDto
import com.iver.blpslab1.api.v1.http.dto.toDto
import com.iver.blpslab1.api.v1.http.dto.toEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import com.iver.blpslab1.service.order.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/order")
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping
    fun createOrder(
        @RequestBody requestOrderDto: RequestOrderDto,
    ): ResponseOrderDto {
        return orderService.createOrder(requestOrderDto.toEntity()).toDto()
    }

    @GetMapping("/{orderId}")
    fun getOrderById(
        @PathVariable orderId: OrderId,
    ) = orderService.getOrder(orderId)

    @PutMapping
    fun updateOrder(
        @RequestBody requestOrderDto: RequestOrderDto,
    ): ResponseOrderDto {
        return orderService.updateOrder(requestOrderDto.toEntity()).toDto()
    }

    @DeleteMapping("/{orderId}")
    fun deleteOrder(
        @PathVariable orderId: OrderId,
    ) = orderService.deleteOrder(orderId)

    @PostMapping("/buy/{orderId}")
    fun buyOrder(
        @PathVariable orderId: OrderId
    ) = orderService.buyOrder(orderId)
}
