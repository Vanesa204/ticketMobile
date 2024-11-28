package com.example.tickets

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.example.tickets.kotlin.GenericResponse
import com.example.tickets.kotlin.LoginResponse
import com.example.tickets.kotlin.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class ModificarContrasenia : AppCompatActivity() {
    private lateinit var contraseniaActual: EditText
    private lateinit var nuevaContrasenia: EditText
    private lateinit var confContrasenia: EditText
    private lateinit var btnCambiarContrasenia: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar_contrasenia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

// Inicialización de los elementos UI
        contraseniaActual = findViewById(R.id.ContraseniaActual)
        nuevaContrasenia = findViewById(R.id.ContraseniaNueva)
        confContrasenia = findViewById(R.id.ContraseniaConfirmada)
        btnCambiarContrasenia = findViewById(R.id.ButModificarContrasenia)


// Configurar el listener para el botón

        btnCambiarContrasenia.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                cambiarContrasenia()
            }
        }
    }

    private suspend fun cambiarContrasenia() {
        //val userDao = UserDao()
        val dni = UserSession.dni//UserDao.currentUser
        val password = contraseniaActual.text.toString().toIntOrNull() ?: 0
        val nuevaContraseniaInt = nuevaContrasenia.text.toString().toIntOrNull() ?: 0
        val confirmarContraseniaInt = confContrasenia.text.toString().toIntOrNull() ?: 0

        if (dni == 0) {
            showMessage("El usuario no puede estar vacío.")
            return
        }
        if (password == 0) {
            showMessage("La contraseña actual no puede estar vacía.")
            return
        }
        if (nuevaContraseniaInt == 0) {
            showMessage("El campo de la nueva contraseña no puede estar vacío.")
            return
        }
        if (nuevaContraseniaInt != confirmarContraseniaInt) {
            showMessage("La nueva contraseña y la confirmación no coinciden.")
            return
        }
        //val resultado = userDao.login(dni, password)
        //if (!resultado)
        // {
        //   showMessage("La contraseña actual es incorrecta")
        //  return
        //}
        try {
            //val cambioResultado = userDao.cambiarContrasena(dni, nuevaContraseniaInt)
            //if (cambioResultado) {

            val responseLogin: Response<LoginResponse> = RetrofitClient.api.login(dni, password)
            if (responseLogin.isSuccessful && responseLogin.body()?.success == true) {
                val responseChangePassword: Response<GenericResponse> =
                    RetrofitClient.api.cambiarContrasenia(dni, nuevaContraseniaInt)
                if (responseChangePassword.isSuccessful && responseChangePassword.body()?.success == true) {
                    showMessage("Contraseña cambiada exitosamente")
                }
            } else {
                showMessage("Error al cambiar la contraseña")
            }
        } catch (e: Exception) {
            showMessage("Ocurrió un error al actualizar la contraseña")
        }
        finish()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

