package ir.hoseinahmadi.frenchpastry.ui.screen.basket

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.h1
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.ShopViewModel
import ir.hoseinahmadi.mydigikala.ui.component.Loading3Dots
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun ConfirmOrder(
    onClick: () -> Unit,
    viewModel: ShopViewModel = hiltViewModel()
) {
    var text by remember {
        mutableStateOf("در حال پرداخت")
    }
    var color by remember {
        mutableStateOf(Color.Black)
    }
    var loading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        launch {
            delay(3000)
            loading = false
            text = "پرداخت موفقیت آمیز بود"
            color = Color(0xff24A751)
        }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp,),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
                Spacer(modifier = Modifier.width(4.dp))
                AnimatedVisibility(visible = loading) {
                    Loading3Dots(isDark = true)
                }
            }

            if (!loading){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp,),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "شماره پرداخت:",
                        style = MaterialTheme.typography.body1,
                        color = color
                    )

                      Text(
                        text = PastryHelper.pastryByLocate(UUID.randomUUID().toString()),
                        style = MaterialTheme.typography.h6,
                        color = color
                    )


                }

                  Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp,),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "کد رهگیری:",
                        style = MaterialTheme.typography.body1,
                        color = color
                    )

                      Text(
                        text = PastryHelper.pastryByLocate(UUID.randomUUID().toString()),
                        style = MaterialTheme.typography.h6,
                        color = color
                    )


                }

                     Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp,),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "کداعلامی جهت تحویل سفارش:",
                        style = MaterialTheme.typography.body1,
                        color = color
                    )

                      Text(
                        text = PastryHelper.pastryByLocate("4564489"),
                        style = MaterialTheme.typography.h6,
                        color = color
                    )


                }




            }
        }



        val all by viewModel.allPriceAndDiscount.collectAsState(initial = TotalDiscountsAndPaid())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp, top = 15.dp)
        ) {
            if (all.totalPaid != 0) {
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
            }


            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                onClick = {
                    onClick()
                    viewModel.deleteAllItem()
                })
            {
                Text(
                    text = "بازگشت به سبد خرید",
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
            }
        }

    }

}
