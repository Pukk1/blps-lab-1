package com.iver.blpslab1.remote.payment

interface PaymentIntegration {
    fun pay(payRequest: PayRequest): String
}