package ui

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import models.TimeUnit
import models.Timer


private val DefaultTimers = listOf(
    Timer("Workout", TimeUnit(15)),
    Timer("Animation", TimeUnit(35)),
    Timer("Design", TimeUnit(25)),
    Timer("Egg", TimeUnit(6, 30)),
    Timer("Meditation", TimeUnit(30)),
)

class TimersScreenModel : ScreenModel {
    private val _timers = MutableStateFlow(DefaultTimers)
    val timers = _timers.asStateFlow()


    fun addNew(timer: Timer) {
        _timers.value += timer
    }
}