package com.bookmarket.extension

import com.bookmarket.controller.request.PostBookRequest
import com.bookmarket.model.CustomerModel
import com.bookmarket.controller.request.PostCustomerRequest
import com.bookmarket.controller.request.PutBookRequest
import com.bookmarket.controller.request.PutCustomerRequest
import com.bookmarket.controller.response.BookResponse
import com.bookmarket.controller.response.CustomerResponse
import com.bookmarket.controller.response.PageResponse
import com.bookmarket.enums.BookStatus
import com.bookmarket.enums.CustomerStatus
import com.bookmarket.model.BookModel
import org.springframework.data.domain.Page
import java.math.BigDecimal
import javax.persistence.*

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(
        name = this.name,
        email = this.email,
        status = CustomerStatus.ATIVO,
        password = this.password
    )
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(
        id = previousValue.id,
        name = this.name,
        email = this.email, previousValue.status,
        password = previousValue.password
    )
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

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}

fun <T> Page<T>.toPageResponse(): PageResponse<T>{
    return PageResponse(
        this.content,
        this.number,
        this.totalElements,
        this.totalPages)
}


