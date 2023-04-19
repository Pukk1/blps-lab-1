package com.iver.blpslab1.service.order

import com.iver.blpslab1.dao.order.OrderEntity
import com.iver.blpslab1.dao.order.OrderId
import com.iver.blpslab1.dao.order.OrderRepository
import com.iver.blpslab1.exception.NotFoundException
import com.iver.blpslab1.remote.payment.PayRequest
import com.iver.blpslab1.remote.payment.PaymentIntegration
import com.iver.blpslab1.service.utils.doTransaction
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val paymentIntegration: PaymentIntegration,
    transactionManager: PlatformTransactionManager,
) : OrderService {

    private val transactionTemplate = TransactionTemplate(transactionManager)

    override fun createOrder(order: OrderEntity): OrderEntity =
        doTransaction(transactionTemplate = transactionTemplate) {
            orderRepository.save(order)
        }

    @Transactional
    override fun updateOrder(order: OrderEntity): OrderEntity =
        doTransaction(transactionTemplate = transactionTemplate) {
            orderRepository.findById(order.id).orElseThrow { NotFoundException("Order not found") }
            orderRepository.save(order)
        }

    override fun getOrder(orderId: OrderId): OrderEntity? {
        return orderRepository.findById(orderId).orElse(null)
    }

    override fun deleteOrder(orderId: OrderId) {
        doTransaction(transactionTemplate = transactionTemplate) {
            orderRepository.deleteById(orderId)
        }
    }

    @Transactional
    override fun buyOrder(orderId: OrderId): String = doTransaction(transactionTemplate = transactionTemplate) {
        val order = getOrder(orderId) ?: throw NotFoundException("Order not found")
        paymentIntegration.pay(
            PayRequest(
                amount = order.coast,
                cardNumber = "478329248239423",
                owner = "fjdsklsd",
                expirationTime = "03/24",
                cvvCode = 473,
            )
        )
    }
}
