package com.bookmarket.extension

import com.bookmarket.controller.request.PostBookRequest
import com.bookmarket.model.CustomerModel
import com.bookmarket.controller.request.PostCustomerRequest
import com.bookmarket.controller.request.PutBookRequest
import com.bookmarket.controller.request.PutCustomerRequest
import com.bookmarket.enums.BookStatus
import com.bookmarket.model.BookModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}


