import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import ui.home.HomeScreen

@Composable
fun App() {
    MaterialTheme(
        colors = lightColors(
            background = Color(1, 13, 63),
            primary = Color(255, 155, 170),
            onPrimary = Color.White,
            onBackground = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .windowInsetsPadding(WindowInsets.systemBars)
                .fillMaxSize()
        ) {
            Navigator(HomeScreen()) {
                SlideTransition(it)
            }
        }
    }
}