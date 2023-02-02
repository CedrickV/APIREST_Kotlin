package com.example.api_rest.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Message(@Id var id: String?, val text: String)
