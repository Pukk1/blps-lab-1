package com.iver.blpslab1.remote.payment

data class PayRequest(
    val amount: Float,
    val cardNumber: String,
    val owner: String,
    val expirationTime: String,
    val cvvCode: Short,
)
