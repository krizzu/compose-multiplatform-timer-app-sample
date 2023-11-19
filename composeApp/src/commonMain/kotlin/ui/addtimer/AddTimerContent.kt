package ui.addtimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.Timer
import ui.addtimer.composables.ClickablePill
import ui.addtimer.composables.SelectableTime
import ui.addtimer.composables.SelectableTimeValue


val HeaderTextStyle = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTimerContent(onBack: () -> Unit, onAddTimer: (timer: Timer) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Back") },
                backgroundColor = MaterialTheme.colors.background,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                })
        }) {
        Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            Text(text = "Select time", style = HeaderTextStyle)
            Spacer(modifier = Modifier.height(24.dp))
            var selectedTime by remember { mutableStateOf<SelectableTimeValue?>(null) }
            FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 4) {
                SelectableTime(
                    SelectableTimeValue.Fifteen,
                    selectedTime == SelectableTimeValue.Fifteen,
                    onClick = { selectedTime = SelectableTimeValue.Fifteen },
                    modifier = Modifier.weight(1f)
                )
                SelectableTime(
                    SelectableTimeValue.Thirty,
                    selectedTime == SelectableTimeValue.Thirty,
                    onClick = { selectedTime = SelectableTimeValue.Thirty },
                    modifier = Modifier.weight(1f)
                )
                SelectableTime(
                    SelectableTimeValue.FortyFive,
                    selectedTime == SelectableTimeValue.FortyFive,
                    onClick = { selectedTime = SelectableTimeValue.FortyFive },
                    modifier = Modifier.weight(1f)
                )
                SelectableTime(
                    SelectableTimeValue.Sixty,
                    selectedTime == SelectableTimeValue.Sixty,
                    onClick = { selectedTime = SelectableTimeValue.Sixty },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(text = "Name", style = HeaderTextStyle)
            var selectedName by remember { mutableStateOf<String?>(null) }

            Spacer(modifier = Modifier.height(24.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                listOf("Workout", "Animation", "Design", "Egg", "Meditation").forEach { name ->
                    key(name) {
                        ClickablePill(
                            text = name,
                            isEnabled = selectedName == name,
                            onClick = { selectedName = name }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    onAddTimer(Timer(name = selectedName!!, time = selectedTime!!.toTimeUnit()))
                },
                enabled = selectedName != null && selectedTime != null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    disabledBackgroundColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(75))
            ) {
                Text(
                    text = "Add timer",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}