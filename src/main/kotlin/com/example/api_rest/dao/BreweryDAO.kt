package com.example.api_rest.dao

import com.example.api_rest.model.Brewery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BreweryDAO : JpaRepository<Brewery, Int> {
    fun findByName(name: String): Brewery?
}