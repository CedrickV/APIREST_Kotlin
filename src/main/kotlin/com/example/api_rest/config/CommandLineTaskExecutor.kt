package com.example.api_rest.config

import com.example.api_rest.dao.BeerDAO
import com.example.api_rest.dao.BreweryDAO
import com.example.api_rest.model.Beer
import com.example.api_rest.model.Brewery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import com.example.api_rest.service.TaskService
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import java.io.FileReader

//Pour l'éxecution

@Profile("!test")
@ConditionalOnProperty(
    prefix = "command.line.runner",
    value = arrayOf("enabled"),
    havingValue = "true",
    matchIfMissing = true)
@Component
class CommandLineTaskExecutor(private val taskService: TaskService): CommandLineRunner {

    @Autowired
    lateinit var breweryDAO: BreweryDAO

    @Autowired
    lateinit var beerDAO : BeerDAO
    override fun run(vararg args: String?) {
        taskService.execute("command line runner task")

        //Breweries.csv parser
        val reader1 = FileReader("../csv/breweries.csv")
        val csvParser1 = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader1)
        val breweryList = mutableListOf<Brewery>()

        for (record: CSVRecord in csvParser1) {
            val id = record.get("id").toInt()
            val adress = record.get("adress1")
            val city = record.get("city")
            val country = record.get("country")
            val desc = record.get("descript")
            val name = record.get("name")

            val brewery = Brewery(id, adress, city, country, desc,name )
            breweryList.add(brewery)
        }
        breweryDAO.saveAll(breweryList)

        //beers.csv parser
        val reader = FileReader("../csv/beers.csv")
        val csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)
        val beerList = mutableListOf<Beer>()

        for (record: CSVRecord in csvParser) {
            val id = record.get("id").toInt()
            val alcoholByVolume = record.get("abv").toDouble()
            val name = record.get("name")
            val brewery_id = record.get("brewery_id").toInt()
            val beer = Beer(id, alcoholByVolume, name, brewery_id)
            beerList.add(beer)
        }
        beerDAO.saveAll(beerList)
    }
}