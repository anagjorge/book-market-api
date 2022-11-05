package com.bookmarket.extension

import com.bookmarket.model.CustomerModel
import com.bookmarket.request.PostCustomerRequest
import com.bookmarket.request.PutCustomerRequest

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name= this.name , email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name= this.name , email = this.email)
}