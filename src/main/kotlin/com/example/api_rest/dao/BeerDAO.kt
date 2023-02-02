package com.example.api_rest.dao

import com.example.api_rest.model.Beer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BeerDAO : JpaRepository<Beer, Int> {
    fun findByName(name: String): Beer?
}