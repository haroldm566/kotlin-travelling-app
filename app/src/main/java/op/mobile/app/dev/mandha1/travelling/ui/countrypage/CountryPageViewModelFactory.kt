package op.mobile.app.dev.mandha1.travelling.ui.countrypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.mandha1.travelling.model.Country

@Suppress("UNCHECKED_CAST")
class CountryPageViewModelFactory(
    private val country: Country
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryPageViewModel::class.java))
            return CountryPageViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
