package org.jkl4o4.expense.tracker.new.data

interface ExpressionEvaluation {

    fun evaluate(expression: String): Double
    fun hasPrecedence(op1: Char, op2: Char): Boolean
    fun applyOperation(op: Char, b: Double, a: Double): Double

    class Base : ExpressionEvaluation {

        override fun evaluate(expression: String): Double {
            val operands = mutableListOf<Double>()

            val operators = mutableListOf<Char>()

            var i = 0
            while (i < expression.length) {
                val ch = expression[i]

                if (ch == ' ') {
                    i++
                    continue
                }

                if (ch.isDigit() || ch == '.') {
                    var numStr = ""
                    while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) {
                        numStr += expression[i]
                        i++
                    }
                    operands.add(numStr.toDouble())
                    i--
                }

                else if (ch == '+' || ch == '-' || ch == '×' || ch == '÷') {
                    while (operators.isNotEmpty() && hasPrecedence(ch, operators.last())) {
                        operands.add(
                            applyOperation(
                                operators.removeAt(operators.size - 1),
                                operands.removeAt(operands.size - 1),
                                operands.removeAt(operands.size - 1)
                            )
                        )
                    }
                    operators.add(ch)
                }
                i++
            }

            while (operators.isNotEmpty()) {
                operands.add(
                    applyOperation(
                        operators.removeAt(operators.size - 1),
                        operands.removeAt(operands.size - 1),
                        operands.removeAt(operands.size - 1)
                    )
                )
            }

            return operands.removeAt(operands.size - 1)
        }

        override fun hasPrecedence(op1: Char, op2: Char): Boolean {
            return when (op2) {
                '×', '÷' -> !(op1 == '×' || op1 == '÷')
                '+', '-' -> false
                else -> false
            }
        }

        override fun applyOperation(op: Char, b: Double, a: Double): Double {
            return when (op) {
                '+' -> a + b
                '-' -> a - b
                '×' -> a * b
                '÷' -> {
                    if (b == 0.0) {
                        throw UnsupportedOperationException("Cannot divide by zero")
                    }
                    a / b
                }

                else -> 0.0
            }
        }
    }
}