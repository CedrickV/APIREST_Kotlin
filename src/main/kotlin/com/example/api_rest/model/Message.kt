package com.example.api_rest.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
@Entity
data class Message(@Id var id: String?, val text: String)
