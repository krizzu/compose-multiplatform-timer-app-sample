package ui.addtimer.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ClickablePill(text: String, isEnabled: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(
        if (isEnabled) MaterialTheme.colors.primary else Color(
            61,
            71,
            100
        )
    )
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .padding(horizontal = 36.dp, vertical = 12.dp)
    )
    {
        Text(text = text, color = Color.White)
    }
}