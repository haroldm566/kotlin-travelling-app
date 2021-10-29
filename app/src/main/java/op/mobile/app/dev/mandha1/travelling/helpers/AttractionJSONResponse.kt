package op.mobile.app.dev.mandha1.travelling.helpers

import com.google.android.gms.maps.model.LatLng

data class AttractionJSONResponse(val name: String, val city: String, val country: String, val location: Location) {
    data class Location (
        val latitude: Double,
        val longitude: Double
    )
}

fun AttractionJSONResponse.toAttraction() = Attraction (
    name = name,
    city = city,
    country = country,
    latLng = LatLng(location.latitude, location.longitude)
)