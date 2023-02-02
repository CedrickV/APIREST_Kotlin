package com.example.api_rest.model

import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name="MESSAGE")
data class Message(@Id val id: String?, val text: String)