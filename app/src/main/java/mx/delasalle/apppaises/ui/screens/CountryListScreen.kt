import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import mx.delasalle.apppaises.model.Country
import mx.delasalle.apppaises.ui.viewmodels.CountryUiState
import mx.delasalle.apppaises.ui.viewmodels.CountryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(navController: NavController,
                      countryViewModel: CountryViewModel= viewModel() ){
    val uiState by countryViewModel.uiState.collectAsState()
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Country List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("second_screen")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Country")
            }
        }
    ){paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)){
            when(uiState){
                is CountryUiState.Loading ->{
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is CountryUiState.Success ->{
                    val countries = (uiState as CountryUiState.Success).countries

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ){
                        items(countries){country ->
                            CountryItem(country)

                        }
                    }
                }
                is CountryUiState.Error ->{
                    val message = (uiState as CountryUiState.Error).message
                    Text(text = message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center))
                }
            }
        }

    }
}

@Composable
fun CountryItem(country: Country){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Row (
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(model = country.image,    contentDescription = country.name,    modifier = Modifier.size(64.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = country.name, style = TextStyle(fontSize = 24.sp))
                Spacer(modifier = Modifier.height(5.dp))
                val capital = country.capital
                Text(text = "Capital ${capital}")
            }
        }
    }
}