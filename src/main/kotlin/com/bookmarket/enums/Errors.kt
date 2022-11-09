package com.bookmarket.enums

enum class Errors(val code: String, val message: String) {

    BM101("ML-101","Book [%s] not exists"),
    BM102("ML-102","Cannot update book with status [%s]"),
    BM201("ML-201","Customer [%s] not exists")

}