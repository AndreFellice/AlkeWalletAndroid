package com.example.alkewalletandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nombres: String? = null,
    var apellidos: String? = null,
    var email: String? = null,
    var password: String? = null
)