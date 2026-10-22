package edu.vt.mobiledev.myapplication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.lifecycle.viewmodel.compose.viewModel

class BurgerViewModel : ViewModel() {

    var selectedBun = mutableStateOf("")
    var selectedPatty = mutableStateOf("")
    val selectedToppings = mutableStateListOf<String>()
    var quantity = mutableStateOf("")

}


@Composable
fun SmallButton(label: String, isSelected: Boolean, imageRes: Int? = null, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(1.dp)
            .height(30.dp)
            .semantics { this.selected = isSelected } // 👈 marks selected in Compose tests
            .testTag(label), // 👈 unique tag for testing
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.Green else Color.Gray
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (imageRes != null) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = "[$label]",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 2.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(label, fontSize = 10.sp)
        }
    }
}


@Composable
fun MyLayout(viewModel: BurgerViewModel = viewModel()) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val context = LocalContext.current
    // ALL STATE UP HERE
    val selectedBun = viewModel.selectedBun.value
    val selectedPatty = viewModel.selectedPatty.value
    val selectedToppings = viewModel.selectedToppings
    val textValue = viewModel.quantity.value

    val toppingImages = mapOf(
        // <a href="https://www.flaticon.com/free-icons/food-and-restaurant" title="food and restaurant icons">Food and restaurant icons created by Royyan Wijaya - Flaticon</a>
        "bacon"     to R.drawable.bacon,
        // <a href="https://www.flaticon.com/free-icons/lettuce" title="lettuce icons">Lettuce icons created by dDara - Flaticon</a>
        "lettuce"   to R.drawable.lettuce,
        // <a href="https://www.flaticon.com/free-icons/tomato" title="tomato icons">Tomato icons created by Pixel perfect - Flaticon</a>
        "tomato"    to R.drawable.tomato,
        // <a href="https://www.flaticon.com/free-icons/pickle" title="pickle icons">Pickle icons created by justicon - Flaticon</a>
        "pickles"   to R.drawable.pickles,
        // <a href="https://www.flaticon.com/free-icons/mayonnaise" title="mayonnaise icons">Mayonnaise icons created by Freepik - Flaticon</a>
        "mayo"      to R.drawable.mayo,
        // <a href="https://www.flaticon.com/free-icons/mustard" title="mustard icons">Mustard icons created by Stockio - Flaticon</a>
        "mustard"   to R.drawable.mustard,
        // <a href="https://www.flaticon.com/free-icons/bbq-sauce" title="bbq sauce icons">Bbq sauce icons created by Freepik - Flaticon</a>
        "bbq sauce" to R.drawable.bbq_sauce
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ROW 1 - Photo
        Image(
            painter = painterResource(R.drawable.burger),
            contentDescription = "Photo",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.FillHeight
        )

        // ROW 2 - Title
        Text(
            text = "Build some burger...",
            fontSize = 24.sp,
            modifier = Modifier.padding(6.dp)
        )

        // Bun/Patty buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "bun",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(2.dp)
                )
                val bunOptions = listOf(
                    "white" to R.drawable.white_bun,
                    "wheat" to R.drawable.wheat_bun,
                    "gluten-free" to R.drawable.gluten_free,
                    "none" to R.drawable.no_bun
                )

                if (isLandscape) {
                    bunOptions.chunked(2).forEach { rowItems ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            rowItems.forEach { (label, image) ->
                                Box(modifier = Modifier.weight(1f)) {
                                    SmallButton(
                                        label,
                                        isSelected = selectedBun == label,
                                        imageRes = image,
                                        modifier = Modifier.testTag("bun_$label")
                                    ) { viewModel.selectedBun.value = label }
                                }
                            }
                        }
                    }
                } else {
                    bunOptions.forEach { (label, image) ->
                        SmallButton(
                            label,
                            isSelected = selectedBun == label,
                            imageRes = image,
                            modifier = Modifier.testTag("bun_$label")
                        ) { viewModel.selectedBun.value = label }
                    }
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "patty",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(2.dp)
                )
                val pattyOptions = listOf(
                    "beef" to R.drawable.beef,
                    "grilled chicken" to R.drawable.chicken,
                    "turkey" to R.drawable.turkey,
                    "salmon" to R.drawable.salmon,
                    "veggie" to R.drawable.veggie
                )

                if (isLandscape) {
                    pattyOptions.chunked(3).forEach { rowItems ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            rowItems.forEach { (label, image) ->
                                Box(modifier = Modifier.weight(1f)) {
                                    SmallButton(
                                        label,
                                        isSelected = selectedPatty == label,
                                        imageRes = image,
                                        modifier = Modifier.testTag("patty_${label.replace(" ", "_")}")
                                    ) { viewModel.selectedPatty.value = label }
                                }
                            }
                        }
                    }
                } else {
                    pattyOptions.forEach { (label, image) ->
                        SmallButton(
                            label,
                            isSelected = selectedPatty == label,
                            imageRes = image,
                            modifier = Modifier.testTag("patty_${label.replace(" ", "_")}")
                        ) { viewModel.selectedPatty.value = label }
                    }
                }
            }
        }

        // Toppings title
        Text(
            text = "toppings",
            fontSize = 16.sp,
            modifier = Modifier.padding(6.dp)
        )

        if (isLandscape) {

            // 🔥 LANDSCAPE → 4 columns (grid)
            val toppingOptions = listOf(
                "bacon", "lettuce", "tomato", "pickles",
                "mayo", "mustard", "bbq sauce"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f)
                    .padding(6.dp)
            ) {
                toppingOptions.chunked(4).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rowItems.forEach { topping ->
                            val isSelected = selectedToppings.contains(topping)

                            Box(modifier = Modifier.weight(1f)) {
                                SmallButton(
                                    label = topping,
                                    isSelected = isSelected,
                                    imageRes = toppingImages[topping],
                                    modifier = Modifier.testTag("topping_${topping.replace(" ", "_")}")
                                ) {
                                    if (isSelected) {
                                        selectedToppings.remove(topping)
                                    } else if (selectedToppings.size < 3) {
                                        selectedToppings.add(topping)
                                    } else {
                                        Toast.makeText(context, "Max 3 toppings allowed!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }

                        // fill empty spots so last row aligns
                        repeat(4 - rowItems.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

        } else {

            // ✅ EXACTLY YOUR ORIGINAL CODE (unchanged)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f)
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    listOf("bacon", "lettuce", "tomato", "pickles").forEach { topping ->
                        val isSelected = selectedToppings.contains(topping)
                        SmallButton(
                            label = topping,
                            isSelected = isSelected,
                            imageRes = toppingImages[topping],
                            modifier = Modifier.testTag("patty_${topping.replace(" ", "_")}")
                        ) {
                            if (isSelected) {
                                selectedToppings.remove(topping)
                            } else if (selectedToppings.size < 3) {
                                selectedToppings.add(topping)
                            } else {
                                Toast.makeText(context, "Max 3 toppings allowed!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    listOf("mayo", "mustard", "bbq sauce").forEach { topping ->
                        val isSelected = selectedToppings.contains(topping)
                        SmallButton(
                            label = topping,
                            isSelected = isSelected,
                            imageRes = toppingImages[topping],
                            modifier = Modifier.testTag("patty_${topping.replace(" ", "_")}")
                        ) {
                            if (isSelected) {
                                selectedToppings.remove(topping)
                            } else if (selectedToppings.size < 3) {
                                selectedToppings.add(topping)
                            } else {
                                Toast.makeText(context, "Max 3 toppings allowed!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

        // Quantity textbox

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "quantity", modifier = Modifier.padding(end = 6.dp))
            OutlinedTextField(
                value = textValue,
                onValueChange = {
                    viewModel.quantity.value = it
                    when (Calculations.isValidQuantity(it)) {
                        QuantityResult.DECIMAL ->
                            Toast.makeText(context, "Whole number only!", Toast.LENGTH_SHORT).show()
                        QuantityResult.NOT_A_NUMBER ->
                            Toast.makeText(context, "Numbers only!", Toast.LENGTH_SHORT).show()
                        QuantityResult.TOO_LOW ->
                            Toast.makeText(context, "Min = 1", Toast.LENGTH_SHORT).show()
                        else -> {}
                    }
                },
                modifier = Modifier
                    .width(80.dp)
                    .height(48.dp) // 👈 realistic minimum for Material3
                    .testTag("quantity_input"),
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }


        // TOTALS COMPUTED FROM STATE
        val qty = if (Calculations.isValidQuantity(textValue) == QuantityResult.VALID) textValue.toInt() else 0
        val (totalCost, totalCalories) = Calculations.calculateTotal(selectedBun, selectedPatty, selectedToppings, qty)
        val totalCostText = Calculations.formatCost(totalCost)
        val totalCaloriesText = Calculations.formatCalories(totalCalories)

        // Bottom text rows
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text("Cost: $totalCostText", fontSize = 14.sp)

                Text("Calories: $totalCaloriesText", fontSize = 14.sp)
            }
        }
    }
}