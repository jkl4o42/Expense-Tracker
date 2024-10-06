package org.jkl4o4.expense.tracker.new.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jkl4o4.expense.tracker.new.presentation.keyboard.CustomKeyboard
import org.jkl4o4.expense.tracker.new.presentation.keyboard.KeyboardKeys
import org.jkl4o4.expense.tracker.new.presentation.keyboard.NumberDisplay
import org.koin.compose.koinInject

@Composable
fun TransactionScreen(
    viewModel: TransactionVideModel = koinInject()
) {

    var inputValue by remember { mutableStateOf(KeyboardKeys.ZERO.label) }
    LaunchedEffect(Unit) {
        viewModel.observeInput {
            inputValue = it
        }
    }

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
            onKeyPress = { viewModel.pressKey(it) }
        )
    }
}