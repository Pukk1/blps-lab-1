package com.iver.blpslab1.remote.payment

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iver.blpslab1.exception.PaymentIntegrationException
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

    final var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val api: PaymentServiceApi = retrofit.create(PaymentServiceApi::class.java)

    override fun pay(payRequest: PayRequest): String {
        val response = api.pay(payRequest).execute()
        if (!response.isSuccessful) {
            throw PaymentIntegrationException()
        }
        return response.body()?.redirectUrl ?: throw PaymentIntegrationException("Empty redirect")
    }

}
