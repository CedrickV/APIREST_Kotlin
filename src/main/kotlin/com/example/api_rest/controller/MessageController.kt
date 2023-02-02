package com.example.api_rest.controller

import com.example.api_rest.dao.repository.MessageDao
import com.example.api_rest.dao.service.MessageService
import com.example.api_rest.model.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MessageController {
    @Autowired
    private lateinit var messageDao: MessageDao

    @GetMapping("/{id}")
    fun index(@PathVariable id: String): ResponseEntity<Any> {
        var message : Message?
        message =messageDao.findById(id = id).orElse(null)
        if (message==null)
            return ResponseEntity(
                hashMapOf<String,String>(Pair("message","not found")),
                HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(message)
    }
}

