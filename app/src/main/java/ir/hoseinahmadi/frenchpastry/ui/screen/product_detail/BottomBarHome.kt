package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.data.model.product_detail.Pastry
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.ui.theme.LightCyan
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.ShopViewModel

@Composable
fun BottomBarHome(
    item: Pastry,
    navHostController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel()
) {


    val isHas by viewModel.isHasIsCart(item.ID).collectAsState(initial = false)
    Column {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray.copy(0.6f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(
                    horizontal = 6.dp,
                    vertical = 7.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!isHas) {
                Button(
                    shape = RoundedCornerShape(9.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                    onClick = { showAddOrder.value = true }) {
                    Text(
                        text = "افزودن به سبد خرید",
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )

                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 12.dp, top = 8.dp, bottom = 8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "موحود در سبد خرید شما",
                        style = MaterialTheme.typography.body2,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        modifier = Modifier.clickable {
                            navHostController.navigate(Screen.BasketScreen.route)
                        },
                        text = "رفتن به سبد خرید",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colorScheme.LightCyan
                    )

                }

            }


            Column(
                Modifier.padding(end = 6.dp),
                horizontalAlignment = Alignment.End,
            ) {
                if (item.has_discount) {
                    Row(
                        Modifier.padding(end = 7.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.Red,
                                    shape = CircleShape
                                )
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .wrapContentHeight(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = PastryHelper.pastryByLocate(item.discount_percent_110n),
                                style = MaterialTheme.typography.h6,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    horizontal = 7.dp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                        Text(
                            text = PastryHelper.pastryByLocateAndSeparator((item.price / 10).toString()),
                            style = MaterialTheme.typography.body2,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough
                        )


                    }
                }

                Text(
                    text = "${PastryHelper.pastryByLocateAndSeparator((item.sale_price / 10).toString())} تومان ",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.SemiBold
                )


            }
        }
    }

}