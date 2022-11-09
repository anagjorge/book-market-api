package com.bookmarket.controller.request

import com.bookmarket.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


data class PostCustomerRequest (
    @field: NotEmpty(message = "Nome deve ser informado")
    var name: String,

    @field:Email(message = "Email deve ser válido")
    @EmailAvailable
    var email: String
)




