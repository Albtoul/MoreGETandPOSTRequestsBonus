package com.example.moregetandpostrequestsbonus
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
interface APIInterface {
    @GET("/test/")
    fun getUser(): Call<ContactNumber>

    @POST("/test/?format=json")
    fun addUser(@Body info: ContactNumber.ContactNumberItem) : Call<ContactNumber.ContactNumberItem>

}

