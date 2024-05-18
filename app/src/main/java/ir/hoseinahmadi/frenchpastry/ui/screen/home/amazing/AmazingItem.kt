package ir.hoseinahmadi.frenchpastry.ui.screen.home.amazing

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.model.home.PastryItem
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.showAddOrder
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.font_bold
import ir.hoseinahmadi.frenchpastry.ui.theme.h3
import ir.hoseinahmadi.frenchpastry.ui.theme.h4
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.PastryHelper.pastryByLocateAndSeparator

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AmazingItem(
    navHostController: NavHostController,
    item: PastryItem
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(14.dp),
        onClick = {             navHostController.navigate(Screen.ProductDetailScreen.route + "?id=${item.ID}")
        },
        modifier = Modifier
            .padding(
                top = 35.dp,
                bottom = 30.dp,
                start = 7.dp
            )
            .width(190.dp)
            .height(215.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(167.dp)
                    .height(100.dp)
            ) {
                GlideImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 2.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(17.dp)),
                    model = item.thumbnail,
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                ){
                    it.placeholder(R.drawable.img_place_holder)
                }
                if (item.has_discount) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopStart) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(50.dp,30.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_off),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = item.discount,
                                    color = Color.White,
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }


                    }

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 7.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${item.title}\n${"(۱ کیلو گرم)"}",
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(2.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    if (item.has_discount) {
                        Text(
                            text = pastryByLocateAndSeparator((item.price / 10).toString()),
                            color = Color.LightGray,
                            textDecoration = TextDecoration.LineThrough,
                            style = MaterialTheme.typography.body1,
                        )
                    } else {
                        Text(
                            text = "", color = Color.LightGray,
                            textDecoration = TextDecoration.LineThrough,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = " ${pastryByLocateAndSeparator((item.sale_price / 10).toString())} ${"تومان"}",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkText

                        )
                        IconButton(
                            modifier = Modifier.size(40.dp),
                            onClick = {

                                navHostController.navigate(Screen.ProductDetailScreen.route + "?id=${item.ID}")
                                showAddOrder.value = true
                            }) {
                            Icon(
                                painterResource(id = R.drawable.img_shopping_card_recycler),
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier.size(35.dp, 25.dp)
                            )
                        }


                    }


                }


            }


        }

    }

}