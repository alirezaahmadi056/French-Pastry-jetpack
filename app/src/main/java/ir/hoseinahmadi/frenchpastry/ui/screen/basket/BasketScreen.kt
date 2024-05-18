package ir.hoseinahmadi.frenchpastry.ui.screen.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.Header
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.h1
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.util.PastryHelper

import ir.hoseinahmadi.frenchpastry.viewModel.ShopViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasketScreen(
    navHostController: NavHostController,
    shopViewModel: ShopViewModel = hiltViewModel()
) {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val item = listOf(
        SteepBasket(
            icon = painterResource(id = R.drawable.ic_shopping_cart_black),
            text = "سبد خرید"
        ),
        SteepBasket(
            icon = painterResource(id = R.drawable.location),
            text = "انتخاب آدرس"
        ),
        SteepBasket(
            icon = painterResource(id = R.drawable.cardpos),
            text = "پرداخت"
        ),

        )
    val pagerState = rememberPagerState { item.size }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

val scope = rememberCoroutineScope()
    Scaffold(
        containerColor = Color(0xffF4F6FF),
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    item.forEachIndexed { index, steepBasket ->
                        val color = if (selectedTabIndex >= index) Color.Black else Color.LightGray
                        TopHead(
                            icon = steepBasket.icon,
                            tint = color,
                            isHaveDivider = index != 2
                        )


                    }


                }
                Header(title = item[selectedTabIndex].text)
            }

        }
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            state = pagerState,
            userScrollEnabled = false
        ) {
            when (it) {
                0 -> {
                    Orders(shopViewModel, onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    })
                }

                1 -> {
                    SelectedAddress(onClick = {scope.launch { pagerState.animateScrollToPage(2) }}, navHostController = navHostController)
                }

                2 -> {
                    ConfirmOrder(onClick = {scope.launch { pagerState.animateScrollToPage(0) }})
                }
            }
        }
    }


}


@Composable
private fun TopHead(
    icon: Painter,
    tint: Color,
    isHaveDivider: Boolean
) {
    Column(
        Modifier.padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(45.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.backbakethed),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = icon,
                        contentDescription = "",
                        modifier = Modifier.size(25.dp),
                        tint = tint
                    )
                }

            }
            if (isHaveDivider) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = tint,
                    modifier = Modifier.width(100.dp)
                )
            }

        }
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Orders(
    shopViewModel: ShopViewModel,
   onClick: () -> Unit
) {
    val allItem by shopViewModel.allItemShop.collectAsState(initial = emptyList())

    Scaffold(
        containerColor = Color(0xffF4F6FF),
        bottomBar = {
            if (allItem.isNotEmpty()){
                BottomBarBasket(
                    shopViewModel,
                    onClick = {onClick() }
                )
            }

        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color(0xffF4F6FF))
        ) {
            item { Spacer(modifier = Modifier.height(18.dp)) }
            if (allItem.isEmpty()) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 7.dp, vertical = 25.dp),
                        text = "سبد خرید شما خالی می باشد !",
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                        )
                }
            }else{
                items(allItem) {
                    CartItemCard(item = it)
                }
            }

        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BottomBarBasket(
    viewModel: ShopViewModel,
    onClick : () ->Unit,
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
                        text = PastryHelper.pastryByLocateAndSeparator((all.totalDiscount /10).toString()),
                        style = MaterialTheme.typography.h2,
                        color = Color(0XFFCF3C3C),
                        )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(painter = painterResource(id = R.drawable.toman),
                        contentDescription ="",
                        Modifier.size(20.dp),
                        tint =  Color(0XFFCF3C3C)
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
                        text = PastryHelper.pastryByLocateAndSeparator((all.totalPaid /10).toString()),
                        style = MaterialTheme.typography.h2,
                        color = Color.Black

                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(painter = painterResource(id = R.drawable.toman),
                        contentDescription ="",
                        Modifier.size(20.dp),
                        tint =  Color.Black
                    )

                }



            }


        }
        val scope = rememberCoroutineScope()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(9.dp),
            onClick = {
                onClick()
            })
        {
            Text(
                text = "انتخاب آدرس",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }

    }

}