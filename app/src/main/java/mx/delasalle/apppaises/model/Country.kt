package mx.delasalle.apppaises.model
    data class Country (
        val name: String,
        val capital: String,
        val image: String
    )

    data class Name(
        val common: String,
        val official: String
    )

    data class Flags(
        val png:String?,
        val svg:String?
    )