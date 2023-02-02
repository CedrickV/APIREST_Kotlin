package com.example.api_rest.controller

import com.example.api_rest.dao.BeerDAO
import com.example.api_rest.model.Beer
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/beers")
class BeerController(private val beerDAO: BeerDAO) {
    @ApiOperation(value = "View a list of available beers", response = List::class)
    @GetMapping
    fun getAllBeers(): List<Beer> = beerDAO.findAll()

    @ApiOperation(value = "Get a beer by id")
    @GetMapping("/{id}")
    fun getBeerById(@PathVariable id: Int): Beer = beerDAO.findById(id).orElseThrow { BeerNotFoundException(id) }

    @ApiOperation(value = "Add a beer")
    @PostMapping
    fun createBeer(@RequestBody beer: Beer): Beer = beerDAO.save(beer)

    @ApiOperation(value = "Update a beer")
    @PutMapping("/{id}")
    fun updateBeer(@PathVariable id: Int, @RequestBody beer: Beer): Beer {
        val existingBeer = beerDAO.findById(id).orElseThrow{BeerNotFoundException(id)}

        existingBeer.name = beer.name
        existingBeer.alcoholByVolume = beer.alcoholByVolume
        existingBeer.id = beer.id
        existingBeer.breweryId = beer.breweryId

        return beerDAO.save(existingBeer)
    }
    @ApiOperation(value = "Delete a beer")
    @DeleteMapping("/{id}")
    fun deleteBeer(@PathVariable id: Int) {
        if (!beerDAO.existsById(id)) {
            throw BeerNotFoundException(id)
        }
        beerDAO.deleteById(id)
    }
}
@Api(value = "Beer not found exception")
class BeerNotFoundException(id: Int) : Exception("La bière que vous rechercher avec l'id : $id n'existe pas")
