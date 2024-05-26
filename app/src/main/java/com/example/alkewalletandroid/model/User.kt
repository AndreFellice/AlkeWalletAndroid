package com.example.alkewalletandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nombres: String,
    var apellidos: String,
    var email: String,
    var password: String,
    val photoUri: String // Nuevo campo para la URI de la foto
)