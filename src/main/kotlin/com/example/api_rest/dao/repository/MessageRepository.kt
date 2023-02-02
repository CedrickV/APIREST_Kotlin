package com.example.api_rest.dao.repository

import com.example.api_rest.model.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>