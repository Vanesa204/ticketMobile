package com.example.tickets

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Cargar : AppCompatActivity() {
    private lateinit var edtTitulo: EditText
    private lateinit var edtDetalle: EditText
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cargar)
        edtTitulo = findViewById(R.id.edtTitulo)
        edtDetalle = findViewById(R.id.edtDetalle)
        btnEnviar = findViewById(R.id.btnEnviar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnEnviar.setOnClickListener {
            val titulo = edtTitulo.text.toString().trim()
            val detalle = edtDetalle.text.toString().trim()

            if (titulo.isEmpty() || detalle.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // Aquí puedes hacer algo con los datos, como enviarlos a un servidor o guardarlos en una base de datos
                Toast.makeText(this, "Ticket enviado: $titulo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}