package com.example.alkewalletandroid.model


import android.database.SQLException
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository para manejar las operaciones relacionadas con los usuarios en la base de datos.
 */
class UserRepository(private val userDao: UserDao) {
    // Objeto para acceder a la base de datos
    //private val userDao: UserDao = AppDataBase.getInstance(context).userDao()
    // LiveData para observar todos los usuarios en la base de datos
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()


    suspend fun insertUser(user: User): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                userDao.insert(user)
            }
            Result.success(Unit)
        } catch (e: SQLException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUser(user: User): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                userDao.update(user)
            }
            Result.success(Unit)
        } catch (e: SQLException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteUser(userId: Int): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                userDao.delete(userId)
            }
            Result.success(Unit)
        } catch (e: SQLException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtiene un usuario por su ID de la base de datos.
     * Esta función retorna un LiveData que contiene el usuario correspondiente al ID proporcionado.
     * El uso de LiveData permite que la UI observe los cambios en el usuario automáticamente.
     *
     * @param userId El ID del usuario a obtener.
     * @return LiveData que contiene el usuario correspondiente al ID proporcionado.
     */
    fun getUserById(userId: Int): LiveData<User> {
        return userDao.getUserById(userId)
    }

    /**
     * Obtiene un usuario por su dirección de correo electrónico de la base de datos.
     * Esta función se debe llamar desde una coroutina ya que es una operación de bloqueo potencial.
     *
     * @param email La dirección de correo electrónico del usuario a obtener.
     * @return El usuario correspondiente a la dirección de correo electrónico proporcionada, o null si no existe.
     */
    suspend fun getUserByEmail(email: String): Result<User?> {
        return try {
            val user = withContext(Dispatchers.IO) {
                userDao.getUserByEmail(email)
            }
            Result.success(user)
        } catch (e: SQLException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtiene un usuario por su dirección de correo electrónico y contraseña de la base de datos.
     * Esta función se debe llamar desde una coroutina ya que es una operación de bloqueo potencial.
     *
     * @param email La dirección de correo electrónico del usuario a obtener.
     * @param password La contraseña del usuario a obtener.
     * @return El usuario correspondiente a la dirección de correo electrónico y contraseña proporcionadas, o null si no existe.
     */
    suspend fun getUserByEmailAndPassword(email: String, password: String): Result<User?> {
        return try {
            val user = withContext(Dispatchers.IO) {
                userDao.getUserByEmailAndPassword(email, password)
            }
            Result.success(user)
        } catch (e: SQLException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


