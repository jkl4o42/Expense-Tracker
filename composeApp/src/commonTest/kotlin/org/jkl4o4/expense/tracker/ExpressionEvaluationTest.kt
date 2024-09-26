package org.jkl4o4.expense.tracker

import org.jkl4o4.expense.tracker.new.data.ExpressionEvaluation
import kotlin.test.Test

import kotlin.test.assertEquals

class ExpressionEvaluationTest {

    private val evaluator = ExpressionEvaluation.Base()

    @Test
    fun `test addition only`() {
        val expression = "10+20"
        val result = evaluator.evaluate(expression)
        assertEquals(30.0, result, "10+20 should equal 30.0")
    }

    @Test
    fun `test subtraction only`() {
        val expression = "30-10"
        val result = evaluator.evaluate(expression)
        assertEquals(20.0, result, "30-10 should equal 20.0")
    }

    @Test
    fun `test multiplication only`() {
        val expression = "10×2"
        val result = evaluator.evaluate(expression)
        assertEquals(20.0, result, "10×2 should equal 20.0")
    }

    @Test
    fun `test division only`() {
        val expression = "20÷4"
        val result = evaluator.evaluate(expression)
        assertEquals(5.0, result, "20÷4 should equal 5.0")
    }

    @Test
    fun `test division by zero`() {
        val expression = "10÷0"
        try {
            evaluator.evaluate(expression)
        }  catch (e: UnsupportedOperationException) {
            assertEquals("Cannot divide by zero", e.message)

        }
    }

    @Test
    fun `test mixed operations with precedence`() {
        val expression = "10+5×2"
        val result = evaluator.evaluate(expression)
        assertEquals(20.0, result, "10+5×2 should equal 20.0 (× has precedence over +)")
    }

    @Test
    fun `test mixed operations without parentheses`() {
        val expression = "50+100÷2"
        val result = evaluator.evaluate(expression)
        assertEquals(100.0, result, "50+100÷2 should equal 100.0 (÷ has precedence over +)")
    }

    @Test
    fun `test multiple mixed operations`() {
        val expression = "50+100×5÷2"
        val result = evaluator.evaluate(expression)
        assertEquals(300.0, result, "50+100×5÷2 should equal 300.0")
    }

    @Test
    fun `test decimal numbers`() {
        val expression = "10.5+5.2×2"
        val result = evaluator.evaluate(expression)
        assertEquals(20.9, result, "10.5+5.2×2 should equal 20.9")
    }
}