package com.example.tickets

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VistapanelActivity : AppCompatActivity() {
    private lateinit var btnCargar: Button
    private lateinit var btnTicket: Button
    private lateinit var btnUsuario: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vistapanel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnCargar = findViewById(R.id.btnCargar)
        btnTicket = findViewById(R.id.btnTicket)
        btnUsuario = findViewById(R.id.btnUsuario)
        val rol = intent.getStringExtra("USUARIO_ROL")

        when (rol) {
            "1" -> {
                btnCargar.visibility = View.GONE
                btnTicket.visibility = View.VISIBLE
                btnUsuario.visibility = View.VISIBLE
            }
            "2" -> {
                btnCargar.visibility = View.GONE
                btnTicket.visibility = View.VISIBLE
                btnUsuario.visibility = View.GONE
            }
            "3" -> {
                btnCargar.visibility = View.VISIBLE
                btnTicket.visibility = View.VISIBLE
                btnUsuario.visibility = View.GONE
            }
            else -> {
                btnCargar.visibility = View.GONE
                btnTicket.visibility = View.GONE
                btnUsuario.visibility = View.GONE
            }
        }


    }
}