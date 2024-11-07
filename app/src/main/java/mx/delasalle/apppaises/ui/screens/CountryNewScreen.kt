package mx.delasalle.apppaises.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.delasalle.apppaises.datasources.RetrofitInstance
import mx.delasalle.apppaises.model.Country

@Composable
//@Preview(showBackground = true)
fun CountryNewScreen(navController: NavController){
    // Variables de estado para los campos de texto
    var name by remember { mutableStateOf("") }
    var capital by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        // Campo de texto para "Name"
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre los campos

        // Campo de texto para "Capital"
        OutlinedTextField(
            value = capital,
            onValueChange = { capital = it },
            label = { Text("Capital") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para "Image"
        OutlinedTextField(
            value = image,
            onValueChange = { image = it },
            label = { Text("Image") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacio antes del botón

        // Botón alineado a la derecha
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { registerCountry(name, capital, image, context,navController) }) {
                Text("Save")
            }
        }
    }
}

fun registerCountry(name: String, capital: String, image: String, context: Context, navController: NavController) {
    val country = Country(name, capital, image)
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.registerCountry(country)
            if (response.isSuccessful) {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "Country registered successfully!", Toast.LENGTH_SHORT).show()
                    navController.navigate("first_screen")
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "Failed to register country", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                println(e.message);
                print("Error: "+e.printStackTrace());

            }
        }
    }
}