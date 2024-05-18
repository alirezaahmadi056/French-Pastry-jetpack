package ir.hoseinahmadi.frenchpastry

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.util.Constants

@Composable
fun MyTopBar(
    navHostController: NavHostController,
    onClick: () ->Unit
) {
    val item = listOf(
        Screen.HomeScreen.route,
        Screen.CategoryScreen.route,
        Screen.BasketScreen.route,
        Screen.PastryScreen.route,
        Screen.ProfileScreen.route
    )

    val backEntry = navHostController.currentBackStackEntryAsState()
    val show = backEntry.value?.destination?.route in item

    AnimatedVisibility(
        visible = show && Constants.CHECKED_LOGIN,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 5.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onClick() }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "",
                    tint = Color.Black
                )
            }

            Image(
                painter = painterResource(id = R.drawable.black_logo),
                contentDescription = "",
                Modifier.size(82.dp, 48.dp),
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_alert), contentDescription = "",
                    Modifier.size(22.dp),
                )
            }

        }
    }


}