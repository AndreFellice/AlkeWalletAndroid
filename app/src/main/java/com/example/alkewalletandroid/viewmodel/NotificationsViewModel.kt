package com.example.alkewalletandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewalletandroid.model.Notification

class NotificationsViewModel : ViewModel() {

    private val _notifications = MutableLiveData<List<Notification>>()
    val notifications: LiveData<List<Notification>>
        get() = _notifications

    // Método para cargar la lista inicial de notificaciones
    fun loadNotifications() {
        val notificationsList = listOf(
            Notification("Transacciones", "Tu transacción de $100.000 ha sido confirmada."),
            Notification("Transacciones", "Tu transacción de $100.000 ha sido cancelada. Intenta nuevamente."),
            Notification("Seguridad", "Se detectó una actividad inusual en tu cuenta. ¿Eras tú?"),
            Notification ("Cambio de contraseña: ","Tu contraseña ha sido actualizada exitosamente."),
            Notification("Cambio en el saldo:", "Tu saldo ha cambiado a $1.200.000."),
            Notification("Información de perfil: ", "Tu información de perfil ha sido actualizada exitosamente."),
            Notification ( "Cambio de nombre de usuario:", "Tu nombre de usuario ha sido actualizado exitosamente."),
            Notification ( "Cambio de correo electrónico:", "Tu correo electrónico ha sido actualizado exitosamente."),
            Notification("Información de ubicación:", "Tu ubicación ha sido actualizada exitosamente."),
            Notification ( "Cambio de número de teléfono:", "Tu número de teléfono ha sido actualizado exitosamente."),
            Notification ( "Cambio de contraseña:", "Tu contraseña ha sido actualizada exitosamente."),
            Notification ("Recordatorio de realizar un pago: ", "Tienes un pago pendiente de $35.000 en tu cuenta.")
        )
        _notifications.value = notificationsList
    }
}