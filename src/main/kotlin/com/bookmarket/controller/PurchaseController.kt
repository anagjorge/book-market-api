package com.bookmarket.controller

import com.bookmarket.controller.mapper.PurchaseMapper
import com.bookmarket.controller.request.PostPurchaseRequest
import com.bookmarket.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("purchases")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest){
        purchaseService.create(purchaseMapper.toModel(request))
    }


}