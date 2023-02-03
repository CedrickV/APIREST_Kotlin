package com.example.api_rest

import com.example.api_rest.controller.BreweryController
import com.example.api_rest.controller.BreweryNotFoundException
import com.example.api_rest.dao.BreweryDAO
import com.example.api_rest.model.Brewery
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

@ExtendWith(MockitoExtension::class)
class BreweryControllerTest {
    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(breweryController).build()

    @Mock
    private lateinit var breweryDAO: BreweryDAO

    @InjectMocks
    private lateinit var breweryController: BreweryController

    private val testBrewery =
        Brewery(1, "Test Brewery", "Test Address", "Test City", "Test Country", "Test Description")

    @Test
    fun getAllBreweriesTest() {
        `when`(breweryDAO.findAll()).thenReturn(listOf(testBrewery))
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Test Brewery"))
    }

    @Test
    fun getBreweryByIdTest() {
        Mockito.`when`(breweryDAO.findById(1)).thenReturn(Optional.of(testBrewery))
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Brewery"))
    }

    @Test
    fun getBreweryByIdNotFoundTest() {
        Mockito.`when`(breweryDAO.findById(1)).thenReturn(Optional.empty())
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    private val breweryId = 1
    private val brewery = Brewery(breweryId, "Brewery1", "Address1", "City1", "Country1", "Description1")
    private val breweryDetails = Brewery(breweryId, "Brewery1 updated", "Address1 updated", "City1 updated", "Country1 updated", "Description1 updated")

    @Test
    fun `updateBrewery should update existing brewery`() {
        `when`(breweryDAO.findById(breweryId)).thenReturn(Optional.of(brewery))
        `when`(breweryDAO.save(brewery)).thenReturn(brewery)

        val result = breweryController.updateBrewery(breweryId, breweryDetails)

        verify(breweryDAO).findById(breweryId)
        verify(breweryDAO).save(brewery)
        assert(result.name == "Brewery1 updated")
        assert(result.address == "Address1 updated")
        assert(result.city == "City1 updated")
        assert(result.country == "Country1 updated")
        assert(result.description == "Description1 updated")
    }

}