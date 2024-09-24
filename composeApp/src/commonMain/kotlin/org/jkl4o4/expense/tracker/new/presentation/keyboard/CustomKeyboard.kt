package org.jkl4o4.expense.tracker.new.presentation.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomKeyboard(
    onKeyPress: (KeyboardKeys) -> Unit,
    modifier: Modifier = Modifier
) {
    val rows = listOf(
        listOf(KeyboardKeys.PLUS, KeyboardKeys.MINUS, KeyboardKeys.MULTIPLICATION, KeyboardKeys.DIVISION, KeyboardKeys.DONE),
        listOf(KeyboardKeys.ONE, KeyboardKeys.TWO, KeyboardKeys.THREE),
        listOf(KeyboardKeys.FOUR, KeyboardKeys.FIVE, KeyboardKeys.SIX),
        listOf(KeyboardKeys.SEVEN, KeyboardKeys.EIGHT, KeyboardKeys.NINE),
        listOf(KeyboardKeys.DECIMAL, KeyboardKeys.ZERO, KeyboardKeys.DELETE)
    )

    Column(
        modifier = modifier.padding(0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        rows.forEachIndexed { i, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                row.forEach { key ->
                    KeyboardKey(
                        isOperation = i == 0,
                        label = key.label,
                        modifier =  Modifier.weight(1f),
                        onClick = { onKeyPress(key) }
                    )
                }
            }
        }
    }
}