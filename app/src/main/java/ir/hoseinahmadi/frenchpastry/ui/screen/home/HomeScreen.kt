package ir.hoseinahmadi.frenchpastry.ui.screen.home

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.model.home.Pastry
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.ui.screen.home.amazing.AmazingOfferSection
import ir.hoseinahmadi.frenchpastry.ui.screen.home.popular.PopularProductSection
import ir.hoseinahmadi.frenchpastry.ui.screen.login.LoginScreen
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.Constants
import ir.hoseinahmadi.frenchpastry.viewModel.HomeViewModel
import ir.hoseinahmadi.frenchpastry.wrapper.DeviceInfo
import ir.hoseinahmadi.mydigikala.ui.component.OurLoading
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    if (Constants.CHECKED_LOGIN) {
        Home(navHostController = navHostController, homeViewModel)
    } else {
        LoginScreen(navHostController = navHostController, homeViewModel)
    }

}

@Composable
fun Home(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel
) {
    LaunchedEffect(true) {
        refreshedMain(homeViewModel)
    }

    var sliderList by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(true)
    }

    var newItemList by remember {
        mutableStateOf<Pastry>(Pastry())
    }

    var amazingItemList by remember {
        mutableStateOf<Pastry>(Pastry())
    }


    var popularItemList by remember {
        mutableStateOf<Pastry>(Pastry())
    }




    val mainResponse by homeViewModel.mainResponse.collectAsState()
    if (mainResponse.success == 1) {
        newItemList = mainResponse.pastries[0]
        amazingItemList = mainResponse.pastries[1]
        popularItemList = mainResponse.pastries[2]
        sliderList =mainResponse.sliders
        loading = false
    }
    val scope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = loading)
    val config = LocalConfiguration.current

    SwipeRefresh(state = swipeRefreshState,
        onRefresh = {
            loading =true
            scope.launch { refreshedMain(homeViewModel = homeViewModel) } })
    {
        if (loading) {
            OurLoading(height = config.screenHeightDp.dp, isDark = true)
        } else {
            if (Constants.USER_NAME == "") {
                AlertInto(navHostController)
            }
            Scaffold(
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(Color(0xffF0F3FF))
                ) {

                    item { TopSliderSection(sliderList) }
                    item {
                        TopProductHeader(
                            title = newItemList.title,
                            onClick = {}
                        )
                    }
                    item { NewProductSection(navHostController, newItemList.pastries) }
                    item { AmazingOfferSection(navHostController, amazingItemList.pastries) }
                    item {
                        TopProductHeader(
                            title = popularItemList.title,
                            onClick = {}
                        )
                    }
                    item { PopularProductSection(navHostController, popularItemList.pastries) }
                    item { Spacer(modifier = Modifier.height(30.dp)) }
                }
            }

        }

    }


}


private suspend fun refreshedMain(homeViewModel: HomeViewModel) {
    homeViewModel.getMain()
}


@Composable
private fun AlertInto(navHostController: NavHostController) {
    val context = LocalContext.current as Activity
    AlertDialog(
        containerColor = Color.White,
        text = {
            Text(
                text = "برای ادامه کار لازمه که اطلاعات هویتی خودتو تکمیل کنی ",
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
        },
        title = {
            Text(
                text = " سلام خوش آمدید",
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        },
        onDismissRequest = { },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                onClick = {
                    navHostController
                        .navigate(Screen.ProfileInfoScreen.route)
                }) {
                Text(
                    text = "بزن بریم",
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    context.finish()
                }) {
                Text(
                    text = "بی خیال",
                    style = MaterialTheme.typography.h6,
                    color = Color.Black
                )
            }
        }
    )

}





