package ui.home.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import models.TimeUnit

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Timer(
    title: String,
    time: TimeUnit,
    modifier: Modifier = Modifier,
    background: Color = Color(61, 71, 100),
    textColor: Color = Color.White,
    height: Dp = 80.dp,
    iconSize: Dp = 18.dp,
    content: (@Composable () -> Unit)? = null
) {
    var enabled by remember { mutableStateOf(false) }
    val timer by produceState(initialValue = time, key1 = enabled) {
        while (enabled) {
            delay(1000)
            value = value.decreaseSecond()
        }
    }
    val backgroundColor by animateColorAsState(if (enabled) Color(255, 155, 170) else background)

    Row(
        modifier = modifier
            .clickable {
                enabled = !enabled
            }
            .height(height)
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 18.dp)
    ) {
        Column {
            Row {
                Text(
                    text = title,
                    color = textColor,
                    fontSize = 14.sp,
                )
                Image(
                    painter = painterResource("pause_icon.xml"),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(textColor),
                    modifier = Modifier.sizeIn(maxWidth = iconSize, maxHeight = iconSize),
                    alpha = if (enabled) 1f else 0f
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = timer.toString(), color = textColor, fontSize = 14.sp)
        }
        if (content != null) {
            Spacer(modifier = Modifier.weight(1f))
            content.invoke()
        }
    }
}


