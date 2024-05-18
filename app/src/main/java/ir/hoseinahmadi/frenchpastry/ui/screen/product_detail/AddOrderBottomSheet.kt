package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.hoseinahmadi.frenchpastry.data.db.entites.ShopEntities
import ir.hoseinahmadi.frenchpastry.data.model.product_detail.Pastry
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.h1
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.ui.theme.h4
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.ShopViewModel
import kotlinx.coroutines.launch

var showAddOrder = mutableStateOf(false)

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun AddOrderBottomSheet(
    item: Pastry,
    shopViewModel: ShopViewModel = hiltViewModel()
) {

    val show by remember {
        showAddOrder
    }
    if (show) {

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

        ModalBottomSheet(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp),
            containerColor = Color(0xffF0F3FF),
            onDismissRequest = { showAddOrder.value = false })
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Header("سفارشی سازی")
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
                                text = "سفارش عادی",
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
                                text = "سفارش عمده",
                                style = MaterialTheme.typography.body1,
                                color = Color.Black
                            )

                        }

                    }


                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    when (it) {
                        0 -> {
                            DefaultOrder(shopViewModel, item)
                        }

                        1 -> {
                            HighOrder(shopViewModel, item)
                        }
                    }
                }

            }
        }
    }


}

@Composable
fun DefaultOrder(shopViewModel: ShopViewModel, item: Pastry) {

    var kilogram by remember {
        mutableIntStateOf(1)
    }

    var taste by remember {
        mutableStateOf("شکلاتی")
    }

    var time by remember {
        mutableStateOf("11-14")
    }

    var flores by remember {
        mutableIntStateOf(1)
    }

    var img = item.gallery
    if (item.gallery ==null||img!!.isEmpty()) {
        img= listOf(
            "https://raw.githubusercontent.com/ihoseinam/video-shop/main/slider1.png",
            "https://raw.githubusercontent.com/ihoseinam/video-shop/main/slider1.png",
        )

    }

    Column {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp, vertical = 7.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    0.6.dp, color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "وزن شیرینی",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                )
                var showMenu by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xffF0F3FF))
                        .padding(horizontal = 14.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PastryHelper.pastryByLocate(" $kilogram کیلو گرم "),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black,
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Icon(Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "",
                            Modifier.clickable { showMenu = true }
                        )

                        DropdownMenu(
                            modifier = Modifier.background(Color(0xffF0F3FF)),
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            val items = 1..4
                            items.forEach {
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(textColor = Color.Black),
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = PastryHelper.pastryByLocate(it.toString()),
                                                style = MaterialTheme.typography.body2,
                                                color = MaterialTheme.colorScheme.darkText,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "کیلو گرم",
                                                style = MaterialTheme.typography.h6,
                                                color = MaterialTheme.colorScheme.darkText
                                            )
                                        }

                                    },
                                    onClick = {
                                        showMenu = false
                                        kilogram = it
                                    })
                            }


                        }
                    }


                }


            }
//2
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "طعم شیرینی",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                )
                var showMenu2 by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xffF0F3FF))
                        .padding(horizontal = 14.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = taste,
                        style = MaterialTheme.typography.body2,
                        color = Color.Black,
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Icon(Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "",
                            Modifier.clickable { showMenu2 = true }
                        )

                        DropdownMenu(
                            modifier = Modifier.background(Color(0xffF0F3FF)),
                            expanded = showMenu2,
                            onDismissRequest = { showMenu2 = false }
                        ) {
                            val items = listOf(
                                "شکلاتی",
                                "وانیلی",
                            )
                            items.forEachIndexed { index, i ->
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(textColor = Color.Black),
                                    text = {
                                        Text(
                                            text = i,
                                            style = MaterialTheme.typography.h6,
                                            color = MaterialTheme.colorScheme.darkText
                                        )
                                    },
                                    onClick = {
                                        showMenu2 = false
                                        taste = i
                                    })
                            }


                        }
                    }


                }


            }

            //3

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "زمان تحویل",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                )
                var showMenu3 by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xffF0F3FF))
                        .padding(horizontal = 14.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PastryHelper.pastryByLocate(time),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black,
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Icon(Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "",
                            Modifier.clickable { showMenu3 = true }
                        )

                        DropdownMenu(
                            modifier = Modifier.background(Color(0xffF0F3FF)),
                            expanded = showMenu3,
                            onDismissRequest = { showMenu3 = false }
                        ) {
                            val items = listOf(
                                "11-14",
                                "5-7",
                                "9-11",
                            )
                            items.forEachIndexed { index, i ->
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(textColor = Color.Black),
                                    text = {
                                        Text(
                                            text = PastryHelper.pastryByLocate(i),
                                            style = MaterialTheme.typography.h6,
                                            color = MaterialTheme.colorScheme.darkText
                                        )
                                    },
                                    onClick = {
                                        showMenu3 = false
                                        time = i
                                    })
                            }


                        }
                    }


                }


            }

            //4

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "تعداد طبقه",
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        text = "(اگر کیک بود)",
                        style = MaterialTheme.typography.h6,
                        color = Color.Red,
                    )


                }
                var showMenu3 by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xffF0F3FF))
                        .padding(horizontal = 14.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PastryHelper.pastryByLocate(flores.toString()),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black,
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Icon(Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "",
                            Modifier.clickable { showMenu3 = true }
                        )

                        DropdownMenu(
                            modifier = Modifier.background(Color(0xffF0F3FF)),
                            expanded = showMenu3,
                            onDismissRequest = { showMenu3 = false }
                        ) {
                            val items = 1..3
                            items.forEachIndexed { index, i ->
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(textColor = Color.Black),
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = PastryHelper.pastryByLocate((index + 1).toString()),
                                                style = MaterialTheme.typography.body2,
                                                color = MaterialTheme.colorScheme.darkText,
                                                fontWeight = FontWeight.Bold
                                            )

                                        }

                                    },
                                    onClick = {
                                        showMenu3 = false
                                        flores = index + 1
                                    })
                            }
                        }
                    }
                }


            }

        }
        val price = (item.sale_price * kilogram) / 10
        val priceDiscount = (item.price * kilogram) / 10
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "مبلغ نهایی",
                style = MaterialTheme.typography.h4,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (item.has_discount) {
                    Text(
                        text = PastryHelper.pastryByLocateAndSeparator(priceDiscount.toString()),
                        style = MaterialTheme.typography.h2,
                        color = Color.DarkGray.copy(0.7f),
                        textDecoration = TextDecoration.LineThrough
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                }
                Text(
                    text = PastryHelper.pastryByLocateAndSeparator(price.toString()),
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff24A751)
                )
                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "تومان",
                    style = MaterialTheme.typography.h6,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }


        }
        val context = LocalContext.current
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 3.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                shopViewModel.addShopOrder(
                    ShopEntities(
                        id = item.ID,
                        price = item.price,
                        discount = item.discount_percent,
                        title = item.title,
                        img = img!![0],
                        count = kilogram
                    )
                )
                Toast.makeText(context, "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show()
                showAddOrder.value = false
            })
        {
            Text(
                text = "افزودن به سبد خرید (عادی)",
                style = MaterialTheme.typography.body1,
                color = Color.White,
            )
        }
    }

}

@Composable
fun HighOrder(shopViewModel: ShopViewModel, item: Pastry) {

    var kilogram by remember {
        mutableIntStateOf(10)
    }

    var taste by remember {
        mutableStateOf("شکلاتی")
    }

    var time by remember {
        mutableStateOf("شنبه")
    }
    var img = item.gallery
    if (item.gallery ==null||img!!.isEmpty()) {
        img= listOf(
            "https://raw.githubusercontent.com/ihoseinam/video-shop/main/slider1.png",
            "https://raw.githubusercontent.com/ihoseinam/video-shop/main/slider1.png",
        )

    }

    Column {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp, vertical = 7.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    0.6.dp, color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "وزن شیرینی",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                )
                var showMenu by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xffF0F3FF))
                        .padding(horizontal = 14.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PastryHelper.pastryByLocate(" $kilogram کیلو گرم "),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black,
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Icon(Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "",
                            Modifier.clickable { showMenu = true }
                        )

                        DropdownMenu(
                            modifier = Modifier.background(Color(0xffF0F3FF)),
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            val items = 1..4
                            items.forEach {
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(textColor = Color.Black),
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = PastryHelper.pastryByLocate((it * 10).toString()),
                                                style = MaterialTheme.typography.body2,
                                                color = MaterialTheme.colorScheme.darkText,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "کیلو گرم",
                                                style = MaterialTheme.typography.h6,
                                                color = MaterialTheme.colorScheme.darkText
                                            )
                                        }

                                    },
                                    onClick = {
                                        showMenu = false
                                        kilogram = it * 10
                                    })
                            }


                        }
                    }


                }


            }
//2
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "طعم شیرینی",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                )
                var showMenu2 by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xffF0F3FF))
                        .padding(horizontal = 14.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = taste,
                        style = MaterialTheme.typography.body2,
                        color = Color.Black,
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Icon(Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "",
                            Modifier.clickable { showMenu2 = true }
                        )

                        DropdownMenu(
                            modifier = Modifier.background(Color(0xffF0F3FF)),
                            expanded = showMenu2,
                            onDismissRequest = { showMenu2 = false }
                        ) {
                            val items = listOf(
                                "شکلاتی",
                                "وانیلی",
                            )
                            items.forEachIndexed { index, i ->
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(textColor = Color.Black),
                                    text = {
                                        Text(
                                            text = i,
                                            style = MaterialTheme.typography.h6,
                                            color = MaterialTheme.colorScheme.darkText
                                        )
                                    },
                                    onClick = {
                                        showMenu2 = false
                                        taste = i
                                    })
                            }


                        }
                    }


                }


            }

            //3

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "زمان تحویل",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                )
                var showMenu3 by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xffF0F3FF))
                        .padding(horizontal = 14.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PastryHelper.pastryByLocate(time),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black,
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Icon(Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "",
                            Modifier.clickable { showMenu3 = true }
                        )

                        DropdownMenu(
                            modifier = Modifier.background(Color(0xffF0F3FF)),
                            expanded = showMenu3,
                            onDismissRequest = { showMenu3 = false }
                        ) {
                            val items = listOf(
                                "11-14",
                                "5-7",
                                "9-11",
                            )
                            items.forEachIndexed { index, i ->
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(textColor = Color.Black),
                                    text = {
                                        Text(
                                            text = PastryHelper.pastryByLocate(i),
                                            style = MaterialTheme.typography.h6,
                                            color = MaterialTheme.colorScheme.darkText
                                        )
                                    },
                                    onClick = {
                                        showMenu3 = false
                                        time = i
                                    })
                            }


                        }
                    }


                }


            }
//4
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ساعت",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                )
                var selected by remember {
                    mutableIntStateOf(1)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xff24A751),
                            unselectedColor = Color.DarkGray
                        ),
                        selected = selected == 1,
                        onClick = { selected = 1 }
                    )
                    Text(
                        text = PastryHelper.pastryByLocate("9 الی 14"),
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    RadioButton(
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xff24A751),
                            unselectedColor = Color.DarkGray
                        ),
                        selected = selected == 2,
                        onClick = { selected = 2 }
                    )
                    Text(
                        text = PastryHelper.pastryByLocate(" 17 الی 22"),
                        style = MaterialTheme.typography.body2
                    )
                }
            }

        }

        val price = (item.sale_price * kilogram) / 10
        val priceDiscount = (item.price * kilogram) / 10
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "مبلغ نهایی",
                style = MaterialTheme.typography.h4,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (item.has_discount) {
                    Text(
                        text = PastryHelper.pastryByLocateAndSeparator(priceDiscount.toString()),
                        style = MaterialTheme.typography.h2,
                        color = Color.DarkGray.copy(0.7f),
                        textDecoration = TextDecoration.LineThrough
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                }
                Text(
                    text = PastryHelper.pastryByLocateAndSeparator(price.toString()),
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff24A751)
                )
                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "تومان",
                    style = MaterialTheme.typography.h6,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }


        }
        val context = LocalContext.current
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 3.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                shopViewModel.addShopOrder(
                    ShopEntities(
                        id = item.ID,
                        price = item.price,
                        discount = item.discount_percent,
                        title = item.title,
                        img = img!![0],
                        count = kilogram
                    )
                )
                Toast.makeText(context, "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show()
                showAddOrder.value = false
            }) {
            Text(
                text = "افزودن به سبد خرید (عمده)",
                style = MaterialTheme.typography.body1,
                color = Color.White,
            )
        }
    }

}
