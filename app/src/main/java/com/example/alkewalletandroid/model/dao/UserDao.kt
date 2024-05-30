package com.example.alkewalletandroid.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.alkewalletandroid.model.entities.User

@Dao
interface UserDao {
    // Obtiene todos los usuarios de la base de datos
    @Query("SELECT * FROM usuarios")
    fun getAllUsers(): LiveData<List<User>>

    // Inserta un nuevo usuario en la base de datos
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    // Actualiza un usuario existente en la base de datos
    @Update
    suspend fun update(user: User)

    // Elimina un usuario de la base de datos por su ID
    @Query("DELETE FROM usuarios WHERE id = :userId")
    suspend fun delete(userId: Int)

    // Obtiene un usuario de la base de datos por su ID
    @Query("SELECT * FROM usuarios WHERE id = :userId")
    fun getUserById(userId: Int): LiveData<User>

    // Obtiene un usuario de la base de datos por su dirección de correo electrónico
    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    // Obtiene un usuario de la base de datos por su dirección de correo electrónico y su contraseña
    @Query("SELECT * FROM usuarios WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

}