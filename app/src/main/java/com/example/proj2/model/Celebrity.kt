package com.example.proj2.model




data class Celebrity(
    val name: String,
    val occupation: List<String>?=null, // Обновлено для соответствия API
    val birthday: String,
    val nationality: String,
    val gender: String,
    val net_worth: Long?, // Добавлено, сделано nullable на случай отсутствия данных
    val height: Double?
)
