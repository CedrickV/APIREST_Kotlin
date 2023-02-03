package com.example.api_rest.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@ApiModel(description = "Description d'une brasserie")
@Entity
@Table(name = "Brewery")
data class Brewery(

    @Id
    @ApiModelProperty(notes = "L'unique identifiant de la brasserie")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @ApiModelProperty(notes = "L'adresse de la brasserie")
    @Column(nullable = false)
    var address: String,

    @ApiModelProperty(notes = "La ville d'une brasserie")
    @Column(nullable = false)
    var city: String,

    @ApiModelProperty(notes = "Le pays d'appartenance d'une brasserie")
    @Column(nullable = false)
    var country: String,

    @ApiModelProperty(notes = "Le description d'une brasserie")
    @Column(nullable = false)
    var description: String,

    @ApiModelProperty(notes = "Le nom d'une brasserie")
    @Column(nullable = false)
    var name: String,

)