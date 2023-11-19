package ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.TimeUnit
import models.Timer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.home.composables.Timer


val HeaderTextStyle = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)

@OptIn(ExperimentalLayoutApi::class, ExperimentalResourceApi::class)
@Composable
fun HomeContent(
    timers: List<Timer>,
    onAddNewTimer: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNewTimer,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
        ) {
            Text(text = "Most used timer", style = HeaderTextStyle)
            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Timer(
                    title = "Breakfast",
                    time = TimeUnit(15),
                    background = Color(255, 237, 237),
                    textColor = Color.Black,
                    modifier = Modifier.aspectRatio(4 / 1f),
                    content = {
                        Image(
                            painter = painterResource("breakfast.xml"),
                            modifier = Modifier.requiredSize(80.dp),
                            contentDescription = null
                        )
                    }
                )
                Timer(
                    title = "Lunch",
                    background = Color(140, 183, 255),
                    textColor = Color.Black,
                    time = TimeUnit(60), modifier = Modifier.weight(1f)
                )
                Timer(
                    title = "Coffee",
                    time = TimeUnit(30),
                    background = Color(160, 222, 187),
                    textColor = Color.Black,
                    modifier = Modifier.weight(2f),
                    content = {
                        Image(
                            painter = painterResource("coffee.xml"),
                            modifier = Modifier.requiredSize(64.dp),
                            contentDescription = null
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Other timer", style = HeaderTextStyle)

            FlowRow(
                maxItemsInEachRow = 3,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                timers.forEach { timer ->
                    key(timer.name + timer.time) {
                        Timer(title = timer.name, time = timer.time, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}