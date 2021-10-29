package op.mobile.app.dev.mandha1.travelling.helpers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import op.mobile.app.dev.mandha1.travelling.R
import java.io.InputStream
import java.io.InputStreamReader

class AttractionJSONReader(private val context: Context) {
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.data)

    fun read(): List<Attraction> {
        val itemType = object : TypeToken<List<AttractionJSONResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson<List<AttractionJSONResponse>>(reader, itemType).map {
            it.toAttraction()
        }
    }
}
