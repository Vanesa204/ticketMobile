package com.example.tickets

import android.content.Intent // Para las intenciones de navegación
import android.os.Bundle // Para el manejo del ciclo de vida de las actividades
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity // Para compatibilidad de la activida
import androidx.core.view.ViewCompat // Para compatibilidad de vistas
import androidx.core.view.WindowInsetsCompat // Para manejo de los insets de ventanas
import android.widget.Button // Para los botones en la UI
import android.widget.EditText // Para los campos de texto
import android.widget.Toast // Para mostrar mensajes rápidos
import com.example.tickets.kotlin.LoginResponse
import com.example.tickets.kotlin.RetrofitClient
import kotlinx.coroutines.CoroutineScope // Para el uso de coroutines
import kotlinx.coroutines.Dispatchers // Para el manejo de los dispatchers
import kotlinx.coroutines.launch // Para lanzar coroutines
import retrofit2.Response // Para manejar respuestas de Retrofit
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtDni: EditText = findViewById(R.id.edtDni)
        val edtPassword: EditText = findViewById(R.id.edtPassword)
        val btnIniciar: Button = findViewById(R.id.ButIniciar)

        // Al hacer clic en el botón "Iniciar sesión"
        btnIniciar.setOnClickListener {
            val dniString = edtDni.text.toString()
            val passwordString = edtPassword.text.toString()

            // Verificar si los campos están vacíos
            if (dniString.isEmpty() || passwordString.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa todos los campos", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            // Convertir a enteros
            val dni = dniString.toIntOrNull()
            val password = passwordString.toIntOrNull()


            // Verificar que el DNI y la contraseña sean válidos
            if (dni == null || password == null) {
                Toast.makeText(this, "DNI y contraseña deben ser números válidos", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
            }
            Log.d("MainActivity", "DNI: $dni, Password: $password") // Agregar log para depuración
               // Llamada a la API para verificar las credenciales
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        Log.d("MainActivity", "ingreso al try")
                        // Llamada al API para realizar el login
                        val response: Response<LoginResponse> =
                            RetrofitClient.api.login(dni, password)

                        // Verificar la respuesta del servidor
                        if (response.isSuccessful && response.body()?.success == true) {
                            Log.d("MainActivity", "verificando respuesta del servidor")

                            // Guardar el DNI y el rol en la sesión de usuario
                            UserSession.dni = dni
                            UserSession.role = response.body()?.rol ?:0

                            if (dni == password) {
                                val intent = Intent(this@MainActivity, ModificarContrasenia::class.java)
                                startActivity(intent)
                            } else {

                                // Si el login es exitoso, redirigir al panel de vista
                                val intent = Intent(
                                    this@MainActivity,
                                    VistapanelActivity::class.java
                                ).apply {
                                    putExtra("Uuario_rol", UserSession.role)
                                }

                                startActivity(intent)
                            }
                        } else {
                            // Si la respuesta es false, mostrar mensaje de error
                            Toast.makeText(
                                this@MainActivity,
                                "Usuario o contraseña incorrectosxxx",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@MainActivity,
                            "Error: ${e.message}", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
    }



