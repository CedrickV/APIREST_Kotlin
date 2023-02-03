package com.example.api_rest.dao

import com.example.api_rest.model.Brewery
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Api(value = "BrasserieDAO")
interface BreweryDAO : JpaRepository<Brewery, Int> {
    @ApiOperation(value = "Affiche une liste des brasseries disponibles", response = List::class)
    override fun findAll(): List<Brewery>

    @ApiOperation(value = "Trouver une brasserie par nom", response = Brewery::class)
    fun findByName(@ApiParam(value = "Nom de la brasserie", required = true) name: String): Brewery?
}
