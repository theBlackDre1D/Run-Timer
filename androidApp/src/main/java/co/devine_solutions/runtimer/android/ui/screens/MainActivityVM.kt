package co.devine_solutions.runtimer.android.ui.screens

import androidx.lifecycle.ViewModel
import co.devine_solutions.runtimer.android.extensions.doInCoroutine
import co.devine_solutions.runtimer.android.ui.components.TimerComponentConfiguration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MainActivityVM : ViewModel() {

    private val _timers = MutableStateFlow<MutableList<TimerComponentConfiguration>>(mutableListOf())
    val timers: StateFlow<MutableList<TimerComponentConfiguration>>
        get() = _timers

    fun addTimer(timer: TimerComponentConfiguration) {
        doInCoroutine {
            val newList = mutableListOf<TimerComponentConfiguration>()
            newList.addAll(_timers.value)
            newList.add(timer)
            _timers.emit(newList)
        }
    }
}