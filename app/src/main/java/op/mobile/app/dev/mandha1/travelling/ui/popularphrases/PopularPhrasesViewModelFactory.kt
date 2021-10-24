package op.mobile.app.dev.mandha1.travelling.ui.popularphrases

import op.mobile.app.dev.mandha1.travelling.model.Country
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PopularPhrasesViewModelFactory(
    private val country: Country
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularPhrasesViewModel::class.java))
            return PopularPhrasesViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
