package com.example.tickets.kotlin


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Usamos 'object' para asegurarnos de que hay una sola instancia de Retrofit
object RetrofitClient {
    //object RetrofitClient {

    // La URL base de la API
    private const val BASE_URL = "http://192.168.100.5/ticketAM/"  // Reemplaza con la URL de tu servidor

    // Instancia única de Retrofit, se crea solo una vez
    val api: ApiService by lazy {
        // val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // URL base de la API
            .addConverterFactory(GsonConverterFactory.create())  // Convertir respuestas JSON a objetos
            .build()
            .create(ApiService::class.java)

    }
}

    // Instancia única de ApiService, se crea solo una vez
    //val apiService: ApiService by lazy {
     //   retrofitInstance.create(ApiService::class.java)  // Crear la instancia de la API
   // }
//}