package op.mobile.app.dev.mandha1.travelling.ui.settings

// Imports
import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

enum class UIMode {
    LIGHT,
    DARK
}

// We need a reference to the context, i.e., SettingsFragment. Note: you can use this class with an Activity
class SettingsManager(context: Context) {

    // Create a new data store - it will store the mode as key/value pairs, i.e., is_dark_mode: 0 or is_dark_mode: 1
    private val dataStore: DataStore<Preferences> =
        context.applicationContext.createDataStore(DATA_STORE_NAME) // This constant is declared in a companion object at the bottom of this class

    val uiModeFlow: Flow<UIMode> = dataStore.data.catch {
        // Catch any issues that may occur when observing
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences()) // Empty the data store
        } else {
            throw it
        }
    }.map {
        // Elvis operator (?:)...shorthand ternary operator

        // If the is_dark_mode value is 1, then UIMode is set to DARK...if is_dark_mode value is 0, then UIMode is set to LIGHT
        // Remember, these UIMode values are set in the UIMode enum class
        when (it[IS_DARK_MODE] ?: false) {
            true -> UIMode.DARK
            false -> UIMode.LIGHT
        }
    }

    // While observing, set the UIMode that is checked, i.e., this value will be based on whether or not the Switch widget is checked
    suspend fun setUIMode(uiMode: UIMode) {
        dataStore.edit {
            it[IS_DARK_MODE] = when (uiMode) {
                UIMode.LIGHT -> false
                UIMode.DARK -> true
            }
        }
    }

    // The companion object is used to access members of a class without creating
    // an instance of a class. The companion object is only tied to this class. Also,
    // you can relate a companion object to a static method. However, internally,
    // they are very different.
    companion object {
        private const val DATA_STORE_NAME = "settings.pref"
        private val IS_DARK_MODE = preferencesKey<Boolean>("is_dark_mode")
    }
}
