package com.iver.blpslab1.api.v1.http.views

import com.iver.blpslab1.dao.order.AddressEntity
import com.iver.blpslab1.dao.order.AddressId
import javax.validation.constraints.NotBlank

data class AddressView(
    var id: AddressId,
    @NotBlank
    var country: String,
    @NotBlank
    var city: String,
    @NotBlank
    var street: String,
    var homeNumber: Int,
    var flat: Int,
    var comment: String?,
)

fun AddressEntity.toView() = AddressView(
    id = id,
    country = country,
    city = city,
    street = street,
    homeNumber = homeNumber,
    flat = flat,
    comment = comment,
)


