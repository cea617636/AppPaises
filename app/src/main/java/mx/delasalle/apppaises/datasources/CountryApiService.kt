package mx.delasalle.apppaises.datasources

import android.telecom.Call
import mx.delasalle.apppaises.model.Country
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CountryApiServices {
    @GET("api/Country")
    suspend fun getAllCountries(): List<Country>

    @GET("v3.1/all")
    suspend fun getAllCountries(
        @Query("fields") field:String="name,capital,flags"
    ):List<Country>

    @POST("api/Country")
    suspend fun registerCountry(@Body country: Country): Response<Void>
}