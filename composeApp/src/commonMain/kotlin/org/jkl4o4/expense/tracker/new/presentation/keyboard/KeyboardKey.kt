package org.jkl4o4.expense.tracker.new.presentation.keyboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardKey(
    isOperation: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(50.dp),
        border = if (!isOperation) BorderStroke(0.2.dp, Color.Gray) else BorderStroke(0.dp, Color.Gray),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = if (!isOperation) Color.DarkGray else Color.Gray)
    ) {
        Text(text = label, color = Color.White, style = MaterialTheme.typography.h6)
    }
}