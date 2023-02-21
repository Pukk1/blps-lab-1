package com.iver.blpslab1.remote.retrofit2.payment

interface PaymentIntegration {
    fun pay(payRequest: PayRequest)
}
