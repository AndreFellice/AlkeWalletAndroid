package com.example.alkewalletandroid.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.view.adapters.NotificationAdapter
import com.example.alkewalletandroid.viewmodel.NotificationsViewModel


class NotificacionesFragment : Fragment() {

    private lateinit var notificationRecyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notificationsViewModel: NotificationsViewModel
   

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notificaciones, container, false)
        notificationRecyclerView = view.findViewById(R.id.recyclerViewNotificaciones)

        // Configurar el ImageView para actuar como botón de retroceso
        val backArrow = view.findViewById<ImageView>(R.id.atras)
        backArrow.setOnClickListener {
            // Acción de retroceso
            parentFragmentManager.popBackStack()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el ViewModel
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        // Observar los cambios en la lista de notificaciones
        notificationsViewModel.notifications.observe(viewLifecycleOwner, Observer { notifications ->
            // Actualizar el RecyclerView con las nuevas notificaciones
            notificationAdapter = NotificationAdapter(notifications)
            notificationRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = notificationAdapter
            }
        })

        // Cargar las notificaciones inicialmente
        notificationsViewModel.loadNotifications()
    }
}