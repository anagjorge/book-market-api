package com.bookmarket.model

import com.bookmarket.enums.CustomerStatus
import javax.persistence.*

@Entity(name = "customers")
data class CustomerModel (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var name: String,

        @Column
        var email: String,

        @Column
        @Enumerated(EnumType.STRING)
        var status: CustomerStatus

)