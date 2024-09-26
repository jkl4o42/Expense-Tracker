package org.jkl4o4.expense.tracker.new.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jkl4o4.expense.tracker.new.data.ExpressionEvaluation
import org.jkl4o4.expense.tracker.new.presentation.keyboard.CustomKeyboard
import org.jkl4o4.expense.tracker.new.presentation.keyboard.KeyboardKeys
import org.jkl4o4.expense.tracker.new.presentation.keyboard.NumberDisplay

@Composable
fun TransactionScreen() {

    var inputValue by remember { mutableStateOf(KeyboardKeys.ZERO.label) }
    val keyboardHandler = KeyboardHandler.Base()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        NumberDisplay(
            modifier = Modifier.align(Alignment.TopEnd),
            value = inputValue
        )

        CustomKeyboard(
            modifier = Modifier.align(Alignment.BottomCenter),
            onKeyPress = { key ->
               inputValue = keyboardHandler.handleKeyPress(inputValue, key) {
                   val expressionEvaluation = ExpressionEvaluation.Base()
                   val result = expressionEvaluation.evaluate(inputValue)
                   println("result: $result")
               }
            })
    }
}