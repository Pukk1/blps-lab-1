package com.iver.blpslab1.api.v1.delegate

import com.fasterxml.jackson.databind.ObjectMapper
import com.iver.blpslab1.api.v1.http.requests.AddressRequest
import com.iver.blpslab1.api.v1.http.requests.OrderRequest
import com.iver.blpslab1.api.v1.http.requests.toEntity
import com.iver.blpslab1.api.v1.http.views.toView
import com.iver.blpslab1.service.order.OrderService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Service
import javax.inject.Named

@Service
@Named("createOrder")
class CreateOrderDelegate(
    private val orderService: OrderService,
    private val objectMapper: ObjectMapper,
) : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        val addressRequest = AddressRequest(
            id = 0L,
            country = execution.getVariable("country") as String,
            city = execution.getVariable("city") as String,
            street = execution.getVariable("street") as String,
            homeNumber = execution.getVariable("homeNumber") as Int,
            flat = execution.getVariable("flat") as Int,
            comment = execution.getVariable("comment") as String,
        )
        val orderRequest = OrderRequest(
            id = 0L,
            coast = (execution.getVariable("coast") as String).toFloat(),
            phoneNumber = execution.getVariable("phoneNumber") as String,
            email = execution.getVariable("email") as String,
            address = addressRequest,
            items = listOf()
        )

        execution.setVariable("order", objectMapper.writeValueAsString(orderService.createOrder(orderRequest.toEntity()).toView()))
    }
}

@Service
@Named("getOrder")
class GetOrderDelegate(
    private val orderService: OrderService,
    private val objectMapper: ObjectMapper,
) : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        val orderId: Long = (execution.getVariable("orderId") as Int).toLong()
        execution.setVariable("order", objectMapper.writeValueAsString(orderService.getOrder(orderId)?.toView()))
    }
}

@Service
@Named("updateOrder")
class UpdateOrderDelegate(
    private val orderService: OrderService,
    private val objectMapper: ObjectMapper,
) : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        val addressRequest = AddressRequest(
            id = 0L,
            country = execution.getVariable("country") as String,
            city = execution.getVariable("city") as String,
            street = execution.getVariable("street") as String,
            homeNumber = execution.getVariable("homeNumber") as Int,
            flat = execution.getVariable("flat") as Int,
            comment = execution.getVariable("comment") as String,
        )
        val orderRequest = OrderRequest(
            id = (execution.getVariable("orderId") as Int).toLong(),
            coast = execution.getVariable("coast") as Float,
            phoneNumber = execution.getVariable("phoneNumber") as String,
            email = execution.getVariable("email") as String,
            address = addressRequest,
            items = listOf()
        )

        execution.setVariable("order", objectMapper.writeValueAsString(orderService.updateOrder(orderRequest.toEntity()).toView()))
    }
}

@Service
@Named("deleteOrder")
class DeleteOrderDelegate(
    private val orderService: OrderService,
) : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        val orderId: Long = (execution.getVariable("orderId") as Int).toLong()
        orderService.deleteOrder(orderId)
    }
}

@Service
@Named("buyOrder")
class BuyOrderDelegate(
    private val orderService: OrderService,
) : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        val orderId: Long = (execution.getVariable("orderId") as Int).toLong()
        orderService.buyOrder(orderId)
    }
}
