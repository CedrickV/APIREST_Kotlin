package com.example.api_rest.controller

import com.example.api_rest.dao.BreweryDAO
import com.example.api_rest.model.Brewery
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/breweries")
@Api(value = "Breweries management system")
class BreweryController(private val breweryDAO: BreweryDAO) {

    @ApiOperation(value = "View a list of available breweries", response = List::class)
    @GetMapping
    fun getAllBreweries(): List<Brewery> = breweryDAO.findAll()

    @ApiOperation(value = "Get a brewery by id")
    @GetMapping("/{id}")
    fun getBreweryById(@PathVariable(value = "id") breweryId: Int): Brewery = breweryDAO.findById(breweryId).orElseThrow{BreweryNotFoundException(breweryId)}

    @ApiOperation(value = "Add a brewery")
    @PostMapping
    fun addBrewery(@Valid @RequestBody brewery: Brewery): Brewery = breweryDAO.save(brewery)

    @ApiOperation(value = "Update a brewery")
    @PutMapping("/{id}")
    fun updateBrewery(@PathVariable(value = "id") breweryId: Int, @Valid @RequestBody breweryDetails: Brewery): Brewery {

        val brewery = breweryDAO.findById(breweryId).orElseThrow{BreweryNotFoundException(breweryId)}

            brewery.name = breweryDetails.name
            brewery.address = breweryDetails.address
            brewery.city = breweryDetails.city
            brewery.country = breweryDetails.country
            brewery.description = breweryDetails.description
            return breweryDAO.save(brewery)

    }

    @ApiOperation(value = "Delete a brewery")
    @DeleteMapping("/{id}")
    fun deleteBrewery(@PathVariable(value = "id") breweryId: Int) = breweryDAO.deleteById(breweryId)
}
@Api(value = "Brewery not found exception")
class BreweryNotFoundException(id: Int)  : Exception("La brasserie que vous rechercher avec l'id : $id n'existe pas")

