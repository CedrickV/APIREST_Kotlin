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


    @GetMapping("/message")
    fun findMessages():  List<Message> = messageDao.findAll()

    @GetMapping("/message/{id}")
    fun findMessageById(@PathVariable id: String): ResponseEntity<Any> {
        var message : Message?
        message =messageDao.findById(id = id).orElse(null)
        if (message==null)
            return ResponseEntity(
                hashMapOf(Pair("message","not found")),
                HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(message)
    }

    @PostMapping("/message")
    fun addMessage(@RequestBody message: Message): ResponseEntity<Any> {
        val addedMessage: Message? = messageDao.save(message)
        if (addedMessage == null) {
            return ResponseEntity(
                hashMapOf(Pair("message", "failed to add message")),
                HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity.ok(addedMessage)
    }

    @DeleteMapping("/message/{id}")
    fun deleteMessage(@PathVariable id:String): ResponseEntity<Any> {
        var message : Message?
        message = messageDao.findById(id = id).orElse(null)
        if (message == null) {
            return ResponseEntity(
                hashMapOf(Pair("message", "Failed to find message")),
                HttpStatus.INTERNAL_SERVER_ERROR)
        }
        messageDao.delete(message)
        return ResponseEntity.ok("Message supprimer avec succès")
    }
}
