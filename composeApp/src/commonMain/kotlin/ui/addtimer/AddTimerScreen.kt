@file:OptIn(ExperimentalVoyagerApi::class)

package ui.addtimer

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.screenModel.rememberNavigatorScreenModel
import models.Timer
import ui.TimersScreenModel

class AddTimerScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val model = navigator.rememberNavigatorScreenModel { TimersScreenModel() }

        fun onAddNew(timer: Timer) {
            model.addNew(timer)
            navigator.popUntilRoot()
        }

        fun onBack() {
            navigator.popUntilRoot()
        }

        AddTimerContent(onBack = ::onBack, onAddTimer = ::onAddNew)
    }
}