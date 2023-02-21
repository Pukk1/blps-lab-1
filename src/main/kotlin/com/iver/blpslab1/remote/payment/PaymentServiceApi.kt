package com.iver.blpslab1.remote.payment

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentServiceApi {
    @POST("pay-request")
    fun pay(@Body payRequest: PayRequest): Call<PayResponse>
}