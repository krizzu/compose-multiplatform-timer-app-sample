@file:OptIn(ExperimentalVoyagerApi::class)

package ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.screenModel.rememberNavigatorScreenModel
import ui.TimersScreenModel
import ui.addtimer.AddTimerScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val model = navigator.rememberNavigatorScreenModel { TimersScreenModel() }
        val timers by model.timers.collectAsState()

        HomeContent(
            timers = timers,
            onAddNewTimer = {
                navigator.push(AddTimerScreen())
            }
        )
    }
}