package com.example.api_rest.model

import javax.persistence.*

@Entity
@Table(name = "Brewery")
data class Brewery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var city: String,

    @Column(nullable = false)
    var country: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var name: String,

)