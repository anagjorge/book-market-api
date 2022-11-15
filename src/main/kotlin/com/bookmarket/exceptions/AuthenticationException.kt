package com.bookmarket.exceptions

class AuthenticationException(override val message: String, val errorCode: String): Exception() {

}