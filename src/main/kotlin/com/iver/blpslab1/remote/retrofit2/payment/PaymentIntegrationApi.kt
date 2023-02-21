package com.iver.blpslab1.remote.retrofit2.payment

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentIntegrationApi {
    @POST("pay-request")
    fun pay(@Body payRequest: PayRequest): Call<Unit>
}