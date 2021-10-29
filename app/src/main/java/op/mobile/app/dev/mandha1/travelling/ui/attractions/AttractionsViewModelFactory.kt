package op.mobile.app.dev.mandha1.travelling.ui.attractions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.mandha1.travelling.model.Country

@Suppress("UNCHECKED_CAST")
class AttractionsViewModelFactory(
    private val country: Country
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionsViewModel::class.java))
            return AttractionsViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
