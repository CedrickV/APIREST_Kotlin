package com.example.api_rest.dao.repository

import com.example.api_rest.model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageDao: JpaRepository<Message, String> {
    override fun findById(id: String): Optional<Message>
}