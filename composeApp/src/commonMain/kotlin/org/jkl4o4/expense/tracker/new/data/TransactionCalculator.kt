package org.jkl4o4.expense.tracker.new.data

interface TransactionCalculator {

    fun calculateAmount(value: String): Double

    class Base : TransactionCalculator {
        override fun calculateAmount(value: String): Double {
            val sanitizedValue = value.replace(" ", "")

            val numbersAndOperators = mutableListOf<String>()
            var currentNumber = StringBuilder()

            for (char in sanitizedValue) {
                if (char == '+' || char == '-') {
                    if (currentNumber.isNotEmpty()) {
                        numbersAndOperators.add(currentNumber.toString())
                        currentNumber = StringBuilder()
                    }
                    numbersAndOperators.add(char.toString())
                } else {
                    currentNumber.append(char)
                }
            }

            if (currentNumber.isNotEmpty()) {
                numbersAndOperators.add(currentNumber.toString())
            }

            var result = numbersAndOperators[0].toDouble()

            var index = 1
            while (index < numbersAndOperators.size) {
                val operator = numbersAndOperators[index]
                val nextNumber = numbersAndOperators[index + 1].toDouble()

                result = when (operator) {
                    "+" -> result + nextNumber
                    "-" -> result - nextNumber
                    else -> result
                }

                index += 2
            }

            val roundedResult = result.roundToTwoDecimalPlaces()

            return if (roundedResult.isWholeNumber()) {
                roundedResult.toLong().toDouble()  // Remove decimal places if it's a whole number
            } else {
                roundedResult
            }
        }

        private fun Double.roundToTwoDecimalPlaces(): Double {
            return kotlin.math.round(this * 100) / 100
        }

        private fun Double.isWholeNumber(): Boolean {
            return this % 1.0 == 0.0
        }
    }
}