package op.mobile.app.dev.mandha1.travelling.model

data class Attraction (
    val name: String,
    val country: String,
    val city: String,
    val location: Location
    ) {

    data class Location (
        val latitude: Double,
        val longitude: Double
    )
}
