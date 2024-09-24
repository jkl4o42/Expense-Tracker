package org.jkl4o4.expense.tracker.new.presentation.keyboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NumberDisplay(modifier: Modifier = Modifier, value: String) {
    Text(
        text = value,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.End,
        color = Color.White
    )
}