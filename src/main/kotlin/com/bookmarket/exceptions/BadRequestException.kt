package com.bookmarket.exceptions

class BadRequestException(override val message: String, val errorCode: String): Exception() {

}