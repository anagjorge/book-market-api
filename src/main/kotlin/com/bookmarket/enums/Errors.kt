package com.bookmarket.enums

enum class Errors(val code: String, val message: String) {

    BM000("BM-000","Access Denied"),
    BM001("BM-001","Invalid Request"),
    BM101("BM-101","Book [%s] not exists"),
    BM102("BM-102","Cannot update book with status [%s]"),
    BM201("BM-201","Customer [%s] not exists")

}