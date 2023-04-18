package com.iver.blpslab1.api.v1.http

import com.iver.blpslab1.api.v1.http.requests.OrderRequest
import com.iver.blpslab1.api.v1.http.requests.toEntity
import com.iver.blpslab1.api.v1.http.views.OrderView
import com.iver.blpslab1.api.v1.http.views.toView
import com.iver.blpslab1.dao.order.entity.OrderId
import com.iver.blpslab1.service.order.OrderService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/order")
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping
    fun createOrder(
        @RequestBody orderRequest: OrderRequest,
    ): OrderView {
        return orderService.createOrder(orderRequest.toEntity()).toView()
    }

    @GetMapping("/{orderId}")
    fun getOrderById(
        @PathVariable orderId: OrderId,
    ) = orderService.getOrder(orderId)

    @PutMapping
    fun updateOrder(
        @RequestBody orderRequest: OrderRequest,
    ): OrderView {
        return orderService.updateOrder(orderRequest.toEntity()).toView()
    }

    @DeleteMapping("/{orderId}")
    fun deleteOrder(
        @PathVariable orderId: OrderId,
    ) = orderService.deleteOrder(orderId)

    @PostMapping("/buy/{orderId}")
    fun buyOrder(
        @PathVariable orderId: OrderId,
        response: HttpServletResponse,
    ) {
        return response.sendRedirect(orderService.buyOrder(orderId))
    }
}
