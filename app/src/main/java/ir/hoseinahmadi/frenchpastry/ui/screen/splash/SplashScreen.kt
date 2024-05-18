package ir.hoseinahmadi.frenchpastry.ui.screen.splash

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SignalWifiStatusbarConnectedNoInternet4
import androidx.compose.material3.Button
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.h1
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val context = LocalContext.current as Activity

    if (!isOnline(context)) {
        NoInterNet(navHostController, context)
    } else {
        LaunchedEffect(true) {
            delay(2000)
            navHostController.navigate(Screen.HomeScreen.route) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }




    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen_logo),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(modifier = Modifier.fillMaxSize(), Alignment.TopCenter) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 85.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.white_logo),
                    contentDescription = "",
                    modifier = Modifier.size(230.dp, 125.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "شـیرینـی فـرانسوی",
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

@Composable
private fun NoInterNet(
    navHostController: NavHostController,
    context: Activity
) {

    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = { /*TODO*/ },
        icon = {
            Icon(
                Icons.Rounded.SignalWifiStatusbarConnectedNoInternet4,
                contentDescription = "",
                tint = Color.Black
            )
        },
        title = {
            Text(
                text = "لطفا دسترسی به اینترنت را بررسی کنید!",
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        },
        confirmButton = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = {
                        if (isOnline(context)) {
                            navHostController.navigate(Screen.HomeScreen.route) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(context, "عدم اتصال به اینترنت", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                    Text(
                        text = "تلاش مجدد",
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                }

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    onClick = {
                        context.finish()
                    }) {
                    Text(
                        text = "خروج از برنامه",
                        style = MaterialTheme.typography.body2,
                        color = Color.Black
                    )
                }


            }
        })
}

