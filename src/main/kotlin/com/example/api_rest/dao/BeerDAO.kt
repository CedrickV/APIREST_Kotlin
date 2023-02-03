package com.example.api_rest.dao

import com.example.api_rest.model.Beer
import io.swagger.annotations.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Api(value = "BeerDAO")
interface BeerDAO : JpaRepository<Beer, Int> {
    @ApiOperation(value = "Recherche une bière par nom")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "La bière a été trouvée", response = Beer::class),
        ApiResponse(code = 404, message = "La bière n'a pas été trouvée")
    ])
    fun findByName(@ApiParam(value = "Nom de la bière", required = true) name: String): Beer?
}
