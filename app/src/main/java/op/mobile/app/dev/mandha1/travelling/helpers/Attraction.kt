package op.mobile.app.dev.mandha1.travelling.helpers

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Attraction(val latLng: LatLng, val name: String, val city: String, val country: String) : ClusterItem {
    override fun getPosition(): LatLng = latLng
    override fun getTitle(): String = name
    override fun getSnippet(): String = ""
}