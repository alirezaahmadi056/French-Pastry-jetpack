package ir.hoseinahmadi.frenchpastry

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.ui.screen.login.steepLogin
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.Constants
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.DatStoreViewModel

@Composable
fun DrawerContent(
    navHostController: NavHostController,
    onClick: () -> Unit,
    datStoreViewModel: DatStoreViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.85f)
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { onClick() }
                ) {
                    Icon(
                        painterResource(id = R.drawable.close),
                        contentDescription = "",
                        Modifier.size(25.dp),
                        tint = Color.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_navigation_header),
                    contentDescription = "",
                    Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = Constants.USER_NAME,
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = PastryHelper.pastryByLocate(Constants.USER_PHONE),
                            color = Color.White,
                            style = MaterialTheme.typography.body1
                        )


                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            TopItem(
                icon = painterResource(id = R.drawable.ic_user),
                title = "پروفایل کاربری",
                onClick = {
                    onClick()
                    navHostController.navigate(Screen.ProfileScreen.route)
                }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray.copy(0.5f),
                modifier = Modifier.padding(vertical = 2.dp)
            )
            TopItem(
                icon = painterResource(id = R.drawable.ic_orders),
                title = "سفارش های من",
                onClick = {
                    Toast.makeText(context, "سفارش فعالی ندارید!", Toast.LENGTH_SHORT).show()
                }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray.copy(0.5f),
                modifier = Modifier.padding(vertical = 2.dp)
            )

            val uriHandler = LocalUriHandler.current

            TopItem(
                icon = painterResource(id = R.drawable.ic_support),
                title = "پشتیبانی",
                onClick = {
                    try {
                        uriHandler.openUri("tg://resolve?domain=i_hoseinam")
                    }catch (e:Exception){
                        Toast.makeText(context, "تلگرام یافت نشد", Toast.LENGTH_SHORT).show()
                    }
                }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray.copy(0.5f),
                modifier = Modifier.padding(vertical = 2.dp)
            )

            TopItem(
                icon = painterResource(id = R.drawable.ic_about_us),
                title = "درباره ما",
                onClick = {
                    Toast.makeText(context, "ما گوی ایم !", Toast.LENGTH_SHORT).show()
                }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray.copy(0.5f),
                modifier = Modifier.padding(vertical = 2.dp)
            )

            TopItem(
                icon = painterResource(id = R.drawable.ic_contact_us),
                title = "ارتباط با ما",
                onClick = {
                    try {
                        uriHandler.openUri("tg://resolve?domain=i_hoseinam")
                    }catch (e:Exception){
                        Toast.makeText(context, "تلگرام یافت نشد", Toast.LENGTH_SHORT).show()
                    }

                }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray.copy(0.5f),
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, "آپدیتی وجود ندارد!", Toast.LENGTH_SHORT).show()
                }) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_upgrade),
                            contentDescription = "",
                            Modifier.size(28.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = "بروزرسانی نرم افزار",
                            style = MaterialTheme.typography.body2,
                            color = Color.Black
                        )

                    }

                    Text(
                        text = "۱.۰.۰",
                        style = MaterialTheme.typography.h6,
                        color = Color.DarkGray
                    )


                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray.copy(0.5f),
                modifier = Modifier.padding(vertical = 2.dp)
            )

            TopItem(
                icon = painterResource(id = R.drawable.ic_exit),
                title = "خروج از حساب کاربری",
                onClick = {
                    datStoreViewModel.saveUserName("")
                    datStoreViewModel.saveUserLogin(false)
                    datStoreViewModel.saveUserPhone("")
                    datStoreViewModel.saveUserApiKey("")
                    Constants.API_KEY =""
                    Constants.CHECKED_LOGIN =false
                    Constants.USER_NAME =""
                    onClick()
                    steepLogin.intValue =1
                    navHostController.navigate(Screen.HomeScreen.route)
                }
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_navigation),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp),
                        contentScale = ContentScale.FillBounds
                    )

            }
        }

    }


}

@Composable
fun TopItem(
    icon: Painter,
    title: String,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClick() }) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = icon, contentDescription = "",
                Modifier.size(28.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
        }
    }


}