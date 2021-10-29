package op.mobile.app.dev.mandha1.travelling.ui.attractions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.mandha1.travelling.model.Country

class AttractionsViewModel(_country: Country) : ViewModel() {
    var country: Country = _country

    init {
        viewModelScope.launch {

        }
    }
}
