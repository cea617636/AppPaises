package mx.delasalle.apppaises.datasources

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://4.153.21.26:8080/"

    private val gson: Gson = GsonBuilder(). setLenient().create()
    private  val client: OkHttpClient = OkHttpClient.Builder().build()

    val api:CountryApiServices by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CountryApiServices::class.java)
    }
}