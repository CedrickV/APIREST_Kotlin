package com.example.api_rest.model

import javax.persistence.*

@Entity
@Table(name = "Beer")
data class Beer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Column(nullable = false)
    var alcoholByVolume: Double,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = true)
    var breweryId : Int
)

