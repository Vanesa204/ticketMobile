package com.example.tickets.kotlin

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login2.php")
    suspend fun login(
        @Field("dni") dni: Int,
        @Field("password") password: Int
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("cambiarContrasena.php")
    suspend fun cambiarContrasenia(
        @Field("dni") dni: Int,
        @Field("newPassword") newPassword: Int
    ): Response<GenericResponse>
}