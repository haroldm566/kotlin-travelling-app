package op.mobile.app.dev.mandha1.travelling.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.mandha1.travelling.api.RetrofitInstance
import op.mobile.app.dev.mandha1.travelling.api.ServiceStatus
import op.mobile.app.dev.mandha1.travelling.model.Country

class HomeViewModel : ViewModel() {
//    private val _count = MutableLiveData<Int>()
//    val count: LiveData<Int> get() = _count
//
//    init {
//        reset()
//    }
//
//    fun plusOne() {
//        _count.value = _count.value?.plus(1)
//    }
//
//    fun reset() {
//        _count.value = 0
//    }
    private val baseUrl =
    "https://gist.githubusercontent.com/mandha1/d697d348476cebcde2d2750712c3438e"


    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<List<Country>>()
    val response: LiveData<List<Country>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitCountryService.getResponse()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}