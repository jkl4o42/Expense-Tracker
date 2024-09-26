package org.jkl4o4.expense.tracker.new.presentation

import org.jkl4o4.expense.tracker.new.presentation.keyboard.KeyboardKeys

interface KeyboardHandler {

    fun handleKeyPress(
        inputValue: String,
        key: KeyboardKeys,
        onDoneClick: () -> Unit
    ): String

    fun deleteKeyPress()
    fun decimalKeyPress(key: KeyboardKeys)
    fun operationKeyPress(key: KeyboardKeys)
    fun otherKeysPress(key: KeyboardKeys)

    class Base : KeyboardHandler {

        private var updatedValue: String = KeyboardKeys.ZERO.label
        private val operations = listOf(
            KeyboardKeys.PLUS,
            KeyboardKeys.MINUS,
            KeyboardKeys.MULTIPLICATION,
            KeyboardKeys.DIVISION
        )

        override fun handleKeyPress(
            inputValue: String,
            key: KeyboardKeys,
            onDoneClick: () -> Unit
        ): String {
            updatedValue = inputValue
            when (key) {
                KeyboardKeys.DECIMAL -> decimalKeyPress(key)
                KeyboardKeys.DONE -> onDoneClick.invoke()
                KeyboardKeys.DELETE -> deleteKeyPress()
                KeyboardKeys.PLUS, KeyboardKeys.MINUS, KeyboardKeys.MULTIPLICATION, KeyboardKeys.DIVISION -> operationKeyPress(
                    key
                )

                else -> otherKeysPress(key)
            }
            return updatedValue
        }

        override fun deleteKeyPress() {
            if (updatedValue.isNotEmpty()) {
                if (updatedValue == KeyboardKeys.ZERO.label) return
                updatedValue = if (updatedValue.length == 1) KeyboardKeys.ZERO.label
                else updatedValue.dropLast(1)
            }
        }

        override fun decimalKeyPress(key: KeyboardKeys) {
            if (updatedValue.last().toString() in operations.map { it.label }) {
                updatedValue += KeyboardKeys.ZERO.label + key.label
                return
            }
            val numberList = updatedValue.split(Regex("[+\\-]"))
            val lastNumber = numberList.last()
            if (!lastNumber.contains(KeyboardKeys.DECIMAL.label)) {
                updatedValue += key.label
            }
        }

        override fun operationKeyPress(key: KeyboardKeys) {
            val lastChar = updatedValue.last().toString()
            if (lastChar !in operations.map { it.label }) {
                updatedValue += key.label
            }
        }

        override fun otherKeysPress(key: KeyboardKeys) {
            val numberList = updatedValue.split(Regex("[+\\-]"))
            val lastNumber = numberList.last()
            if (updatedValue == KeyboardKeys.ZERO.label && key == KeyboardKeys.ZERO) return
            if (updatedValue == KeyboardKeys.ZERO.label) {
                updatedValue = key.label
                return
            }
            if (numberList.size > 1 && lastNumber == KeyboardKeys.ZERO.label) {
                updatedValue += KeyboardKeys.DECIMAL.label + key.label
                return
            }
            updatedValue += key.label
        }
    }
}