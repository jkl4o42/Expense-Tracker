package org.jkl4o4.expense.tracker

import org.jkl4o4.expense.tracker.new.presentation.KeyboardHandler
import org.jkl4o4.expense.tracker.new.presentation.keyboard.KeyboardKeys
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.Test

class KeyboardHandlerTest {

    private val keyboardHandler: KeyboardHandler = KeyboardHandler.Base()

    @Test
    fun `test handleKeyPress with decimal key`() {
        val result = keyboardHandler.handleKeyPress("10", KeyboardKeys.DECIMAL) { }
        assertEquals("Pressing decimal key should append '.' to the input", "10.", result)
    }

    @Test
    fun `test handleKeyPress with delete key`() {
        var result = keyboardHandler.handleKeyPress("10", KeyboardKeys.DELETE) { }
        assertEquals("Pressing delete key should remove the last character", "1", result)

        result = keyboardHandler.handleKeyPress("1", KeyboardKeys.DELETE) { }
        assertEquals("Pressing delete key should reset to '0' when one character left", "0", result)

        result = keyboardHandler.handleKeyPress("0", KeyboardKeys.DELETE) { }
        assertEquals("Pressing delete key on '0' should do nothing", "0", result)
    }

    @Test
    fun `test handleKeyPress with operation keys`() {
        val result = keyboardHandler.handleKeyPress("10", KeyboardKeys.PLUS) { }
        assertEquals("Pressing '+' should append the operator to the input", "10+", result)
    }

    @Test
    fun `test handleKeyPress with operation keys ignoring consecutive operations`() {
        var result = keyboardHandler.handleKeyPress("10+", KeyboardKeys.PLUS) { }
        assertEquals("Pressing '+' after another '+' should do nothing", "10+", result)

        result = keyboardHandler.handleKeyPress("10+", KeyboardKeys.MINUS) { }
        assertEquals("Pressing '-' after '+' should do nothing", "10+", result)
    }

    @Test
    fun `test handleKeyPress with other numeric keys`() {
        var result = keyboardHandler.handleKeyPress("0", KeyboardKeys.ONE) { }
        assertEquals("Pressing '1' on '0' should replace '0' with '1'", "1", result)

        result = keyboardHandler.handleKeyPress("10", KeyboardKeys.TWO) { }
        assertEquals("Pressing '2' should append it to the input", "102", result)
    }

    @Test
    fun `test handleKeyPress leading zeros`() {
        val result = keyboardHandler.handleKeyPress("0", KeyboardKeys.ZERO) { }
        assertEquals("Pressing '0' on '0' should not append another '0'", "0", result)
    }
}