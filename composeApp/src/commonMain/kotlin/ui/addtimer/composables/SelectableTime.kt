package ui.addtimer.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import models.TimeUnit

enum class SelectableTimeValue(val value: Int) {
    Fifteen(15),
    Thirty(30),
    FortyFive(45),
    Sixty(60);


    override fun toString(): String = "$value min"

    fun toTimeUnit() = TimeUnit(value)
}

@Composable
fun SelectableTime(
    value: SelectableTimeValue,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val circleColor by animateColorAsState(
            if (isSelected) MaterialTheme.colors.primary else Color(
                37,
                58,
                115
            )
        )

        Canvas(modifier = Modifier.size(80.dp)) {
            drawCircle(color = Color(15, 32, 74))
            drawValueCircle(value = value, color = circleColor)
        }
        Text(text = value.toString(), color = Color.White, textAlign = TextAlign.Center)
    }
}


fun DrawScope.drawValueCircle(value: SelectableTimeValue, color: Color) {
    when (value) {
        SelectableTimeValue.Fifteen -> {
            drawArc(color = color, startAngle = -90f, sweepAngle = 90f, useCenter = true)
        }

        SelectableTimeValue.Thirty -> {
            drawArc(color = color, startAngle = -90f, sweepAngle = 180f, useCenter = true)
        }

        SelectableTimeValue.FortyFive -> {
            drawArc(color = color, startAngle = -90f, sweepAngle = 270f, useCenter = true)
        }

        SelectableTimeValue.Sixty -> {
            drawArc(color = color, startAngle = -90f, sweepAngle = 360f, useCenter = true)
        }
    }
}