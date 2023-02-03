package com.example.api_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class ApiRestApplication


fun main(args: Array<String>) {
    runApplication<ApiRestApplication>(*args)
}
