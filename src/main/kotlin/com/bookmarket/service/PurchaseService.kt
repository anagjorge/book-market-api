package com.bookmarket.service

import com.bookmarket.events.PurchaseEvent
import com.bookmarket.model.PurchaseModel
import com.bookmarket.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val aplicationEventPublisher: ApplicationEventPublisher,
    private val bookService: BookService

) {

    fun create(purchaseModel: PurchaseModel){
        //bookService.r(purchaseModel.books)
        purchaseRepository.save(purchaseModel)
        aplicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }


}
