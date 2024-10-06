package org.jkl4o4.expense.tracker.new.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.jkl4o4.expense.tracker.new.data.ExpressionEvaluation
import org.jkl4o4.expense.tracker.new.presentation.keyboard.KeyboardKeys

class TransactionVideModel(
    private val keyboardHandler: KeyboardHandler,
    private val expressionEvaluation: ExpressionEvaluation
) : ViewModel() {

    private val inputValue: MutableStateFlow<String> = MutableStateFlow(KeyboardKeys.ZERO.label)

    suspend fun observeInput(action: suspend (value: String) -> Unit) {
        inputValue.collect(action)
    }

    fun pressKey(key: KeyboardKeys) = viewModelScope.launch(Dispatchers.IO) {
        val value = inputValue.value
        val result = keyboardHandler.handleKeyPress(value, key) {
            val result = expressionEvaluation.evaluate(value)

        }
        inputValue.emit(result)
    }
}