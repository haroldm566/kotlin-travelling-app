package op.mobile.app.dev.mandha1.travelling.ui.countrypage

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import op.mobile.app.dev.mandha1.travelling.model.Country
import op.mobile.app.dev.mandha1.travelling.model.Quiz

class CountryPageViewModel(_country: Country) : ViewModel() {
    var country: Country = _country

    private val _questionIdx = MutableLiveData<Int>()
    val questionIdx: LiveData<Int> get() = _questionIdx

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz> get() = _quiz

    private val _answers = MutableLiveData<MutableList<String>>()
    val answers: LiveData<MutableList<String>> get() = _answers

    private val _isFinished = MutableLiveData<Boolean>()
    val isFinished: LiveData<Boolean> get() = _isFinished

    private val _time = MutableLiveData<Long>()
    private val time: LiveData<Long> get() = _time

    lateinit var countDownTimer: CountDownTimer

    init {

    }
}