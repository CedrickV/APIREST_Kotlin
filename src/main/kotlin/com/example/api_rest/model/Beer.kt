package com.example.api_rest.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*
@ApiModel(description = "Description d'une bière")
@Entity
@Table(name = "Beer")
data class Beer(
    @Id
    @ApiModelProperty(notes = "L'unique identifiant de la bière")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @ApiModelProperty(notes = "Le taux d'alcool par volume dans une bière")
    @Column(nullable = false)
    var alcoholByVolume: Double,

    @ApiModelProperty(notes = "Le nom d'une bière")
    @Column(nullable = false)
    var name: String,

    @ApiModelProperty(notes = "l'identifiant unique de la brasserie de la bière")
    @Column(nullable = true)
    var breweryId : Int
)

