package com.example.api_rest

import com.example.api_rest.controller.BeerController
import com.example.api_rest.controller.BeerNotFoundException
import com.example.api_rest.dao.BeerDAO
import com.example.api_rest.model.Beer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class BeerControllerTest {
    @Mock
    private lateinit var beerDAO: BeerDAO

    @InjectMocks
    private lateinit var beerController: BeerController

    @Test
    fun `getAllBeers returns a list of beers`() {
        val beers = listOf(Beer(1, 5.0,"Pale Ale",  1))
        `when`(beerDAO.findAll()).thenReturn(beers)

        val response = beerController.getAllBeers()
        assertEquals(beers, response)
    }

    @Test
    fun `getBeerById returns a beer`() {
        val beer = Beer(1, 5.0, "Pale Ale", 1)
        `when`(beerDAO.findById(1)).thenReturn(Optional.of(beer))

        val response = beerController.getBeerById(1)
        assertEquals(beer, response)
    }

    @Test
    fun `getBeerById throws BeerNotFoundException if beer is not found`() {
        `when`(beerDAO.findById(1)).thenReturn(Optional.empty())

        try {
            beerController.getBeerById(1)
        } catch (e: BeerNotFoundException) {
            assertEquals("La bière que vous rechercher avec l'id : 1 n'existe pas", e.message)
        }
    }

    @Test
    fun `createBeer saves a beer and returns it`() {
        val beer = Beer(1, 5.0, "Pale Ale", 1)
        `when`(beerDAO.save(beer)).thenReturn(beer)

        val response = beerController.createBeer(beer)
        assertEquals(beer, response)
    }

    @Test
    fun `updateBeer updates a beer and returns it`() {
        val existingBeer = Beer(1, 5.0, "Pale Ale", 1)
        val updatedBeer = Beer(1, 6.0, "IPA", 2)
        `when`(beerDAO.findById(1)).thenReturn(Optional.of(existingBeer))
        `when`(beerDAO.save(existingBeer)).thenReturn(existingBeer)

        val response = beerController.updateBeer(1, updatedBeer)
        assertEquals(existingBeer, response)
    }

}

