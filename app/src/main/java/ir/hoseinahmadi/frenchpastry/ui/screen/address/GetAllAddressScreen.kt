package ir.hoseinahmadi.frenchpastry.ui.screen.address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.data.model.addres.addredResponse
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.Header
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.viewModel.AddressViewModel
import ir.hoseinahmadi.mydigikala.ui.component.OurLoading
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAllAddressScreen(
    navHostController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var allAddress by remember {
        mutableStateOf<addredResponse>(addredResponse())
    }

    val loading by viewModel.loading.collectAsState(initial = false)


    LaunchedEffect(true) {
        launch {
            viewModel.getAllAddress(context)
            viewModel.allAddress.collectLatest {
                if (it.http_code == 200 && it.addresses != null) {
                    allAddress = it
                }
            }
        }
    }

    val config = LocalConfiguration.current
    if (loading) {
        OurLoading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        Scaffold(
            containerColor = Color(0xffF4F6FF),
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 5.dp,
                            vertical = 8.dp
                        ),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                }


            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        shape = RoundedCornerShape(9.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        onClick = {
                            navHostController.navigate(Screen.AddAddressScreen.route)
                        }) {
                        Text(
                            text = "افزودن آدرس جدید",
                            style = MaterialTheme.typography.body1,
                            color = Color.White
                        )
                    }
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                item {
                    Header(title = "آدرس های من")
                }
                if (allAddress.addresses!!.isEmpty()) {
                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp),
                            text = "آدرسی برای نمایش وجود ندارد!",
                            style = MaterialTheme.typography.body1,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(
                        items = allAddress.addresses!!,
                        key = { item -> item.ID }
                    ) { address ->
                        AddressCardItem(navHostController, address)
                    }
                }
            }
        }
    }

}
