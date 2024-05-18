package ir.hoseinahmadi.frenchpastry.ui.screen.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.model.addres.Addresse
import ir.hoseinahmadi.frenchpastry.data.model.addres.addredResponse
import ir.hoseinahmadi.frenchpastry.navigation.Screen

import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.AddressViewModel
import ir.hoseinahmadi.frenchpastry.viewModel.ShopViewModel
import ir.hoseinahmadi.mydigikala.ui.component.OurLoading
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectedAddress(
    navHostController: NavHostController,
    onClick: () -> Unit
) {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState { 2 }
    val scope = rememberCoroutineScope()

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    Scaffold(
        containerColor = Color(0xffF4F6FF),
        bottomBar = {
            BottomBarSelectedAddress(onClick = { onClick() })
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(45.dp)
                        .width(180.dp)
                        .padding(horizontal = 4.dp),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        RadioButton(
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xff24A751),
                                unselectedColor = Color.DarkGray
                            ),
                            selected = pagerState.currentPage == 0,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(0)
                                }
                            })
                        Text(
                            text = "ارسال با پیک",
                            style = MaterialTheme.typography.body1,
                            color = Color.Black
                        )

                    }

                }
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(45.dp)
                        .width(180.dp)
                        .padding(horizontal = 4.dp),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        RadioButton(
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xff24A751),
                                unselectedColor = Color.DarkGray
                            ),
                            selected = pagerState.currentPage == 1, onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            })
                        Text(
                            text = "تحویل حضوری",
                            style = MaterialTheme.typography.body1,
                            color = Color.Black
                        )

                    }

                }
            }

            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState
            ) { pagerState ->
                when (pagerState) {
                    0 -> {
                        SendPost(navHostController = navHostController)
                    }

                    1 -> {
                        PersonDelivery()
                    }
                }
            }
        }
    }


}

@Composable
fun SendPost(
    navHostController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel()
) {

    var allAddress by remember {
        mutableStateOf<List<Addresse>>(emptyList())
    }

    val loading by viewModel.loading.collectAsState(initial = false)

    val context = LocalContext.current
    LaunchedEffect(true) {
        launch { viewModel.getAllAddress(context) }
        launch {
            viewModel.allAddress.collectLatest {
                if (it.http_code == 200 && it.addresses != null) {
                    allAddress = it.addresses
                }

            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 11.dp),
                text = "آدرس محل تحویل سفارش خود را انتخاب کنید",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                textAlign = TextAlign.Center

            )
        }
        if (loading) {
            item {
                OurLoading(height = 200.dp, isDark = true)
            }
        } else if (allAddress.isEmpty()) {
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
            itemsIndexed(allAddress) { index, item ->
                SelectedAddressCardItem(item, index)
            }
        }

        item {
            OutlinedButton(
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    navHostController.navigate(Screen.AddAddressScreen.route)
                }) {
                Text(
                    text = "افزودن آدرس جدید",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }
        }
    }


}

@Composable
fun PersonDelivery() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 15.dp),
        text = PastryHelper.pastryByLocate("برای تحویل سفارش از ساعت 8الی 12 و 17 الی 22 می توانید در روز 1403/0/0 به شیرینی فرانسوی واقع در خیابان مدرس ..... مراجعه کنید"),
        style = MaterialTheme.typography.body1,
        color = Color.Black,
        textAlign = TextAlign.Start

    )
}

@Composable
private fun BottomBarSelectedAddress(
    onClick: () -> Unit,
    viewModel: ShopViewModel = hiltViewModel(),
) {

    val all by viewModel.allPriceAndDiscount.collectAsState(initial = TotalDiscountsAndPaid())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 9.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "تخفیف",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PastryHelper.pastryByLocateAndSeparator((all.totalDiscount / 10).toString()),
                        style = MaterialTheme.typography.h2,
                        color = Color(0XFFCF3C3C),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        Modifier.size(20.dp),
                        tint = Color(0XFFCF3C3C)
                    )
                }


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 9.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "مبلغ نهایی",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PastryHelper.pastryByLocateAndSeparator((all.totalPaid / 10).toString()),
                        style = MaterialTheme.typography.h2,
                        color = Color.Black

                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        Modifier.size(20.dp),
                        tint = Color.Black
                    )

                }


            }


        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(9.dp),
            onClick = { onClick() })
        {
            Text(
                text = "پرداخت نهایی",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }

    }

}