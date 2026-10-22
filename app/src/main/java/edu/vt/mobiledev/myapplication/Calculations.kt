package edu.vt.mobiledev.myapplication

object Calculations {

    fun calculateTotal(
        selectedBun: String,
        selectedPatty: String,
        selectedToppings: List<String>,
        qty: Int
    ): Pair<Double, Int> {
        val cost =
            ((foodData[selectedBun]?.price ?: 0.0) +
                    (foodData[selectedPatty]?.price ?: 0.0) +
                    selectedToppings.sumOf { foodData[it]?.price ?: 0.0 }) * qty

        val calories =
            ((foodData[selectedBun]?.calories ?: 0) +
                    (foodData[selectedPatty]?.calories ?: 0) +
                    selectedToppings.sumOf { foodData[it]?.calories ?: 0 }) * qty

        return Pair(cost, calories)
    }

    fun formatCost(cost: Double): String =
        if (cost == 0.0) "not available :((" else "$${"%.2f".format(cost)}"

    fun formatCalories(calories: Int): String =
        if (calories == 0) "not available :((" else "$calories kcal"

    fun isValidQuantity(input: String): QuantityResult {
        return when {
            input.isEmpty()            -> QuantityResult.EMPTY
            input.contains(".")        -> QuantityResult.DECIMAL
            input.toIntOrNull() == null -> QuantityResult.NOT_A_NUMBER
            input.toInt() < 1          -> QuantityResult.TOO_LOW
            else                       -> QuantityResult.VALID
        }
    }
}

enum class QuantityResult {
    VALID, EMPTY, DECIMAL, NOT_A_NUMBER, TOO_LOW
}