package edu.vt.mobiledev.myapplication

data class FoodItem(val price: Double, val calories: Int)

val foodData = mapOf(
    // buns
    "white"           to FoodItem(1.00, 140),
    "wheat"           to FoodItem(1.00, 100),
    "gluten-free"     to FoodItem(2.00, 50),
    "none"            to FoodItem(0.00, 0),
    // patties
    "beef"            to FoodItem(5.50, 240),
    "grilled chicken" to FoodItem(5.00, 180),
    "turkey"          to FoodItem(5.00, 190),
    "salmon"          to FoodItem(7.50, 95),
    "veggie"          to FoodItem(4.50, 80),
    // toppings
    "bacon"           to FoodItem(1.50, 240),
    "lettuce"         to FoodItem(0.30, 20),
    "tomato"          to FoodItem(0.30, 20),
    "pickles"         to FoodItem(0.50, 30),
    "mayo"            to FoodItem(0.00, 100),
    "mustard"         to FoodItem(0.00, 60),
    "bbq sauce"       to FoodItem(0.00, 80),
)
