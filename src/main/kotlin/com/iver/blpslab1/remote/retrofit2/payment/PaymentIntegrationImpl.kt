package com.iver.blpslab1.remote.retrofit2.payment

import com.iver.blpslab1.utils.exceptions.PaymentIntegrationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Service
class PaymentIntegrationImpl(
    @Value("\${paymentIntegration.accessToken}")
    private val ACCESS_TOKEN: String,
    @Value("\${paymentIntegration.url}")
    private val URL: String,
) : PaymentIntegration {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: PaymentIntegrationApi = retrofit.create(PaymentIntegrationApi::class.java)

    override fun pay(payRequest: PayRequest) {
        val response = api.pay(payRequest).execute()
        if (!response.isSuccessful) {
            throw PaymentIntegrationException()
        }
    }

}
