package mx.delasalle.apppaises.repositories

import mx.delasalle.apppaises.datasources.RetrofitInstance
import mx.delasalle.apppaises.model.Country

class CountryRepository {
    private val api = RetrofitInstance.api
    suspend fun getCountries():List<Country>{
        val result = api.getAllCountries()
        return result
    }
}