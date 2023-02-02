package com.example.api_rest.dao.service

import com.example.api_rest.model.Message
import com.example.api_rest.dao.repository.MessageRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService(val db: MessageRepository) {
    fun findMessages(): List<Message> = db.findAll().toList()
    fun findMessageById(id: String): List<Message> = db.findById(id).toList()
    fun save(message: Message) {
        db.save(message)
    }
    fun <T : Any> Optional<out T>.toList(): List<T> =
        if (isPresent) listOf(get()) else emptyList()
}
