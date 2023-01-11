package com.example.mytodoapp

import java.util.Date

data class Note(
     val id: Int,
     val title: String,
     val body: String,
     val category: String,
     val created: Date,
     val updated: Date,
)
