package ir.hoseinahmadi.frenchpastry.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.util.Constants.CHECKED_LOGIN

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController,
) {


    val item = listOf<MyBottomNavItem>(
        MyBottomNavItem(
            route = Screen.HomeScreen.route,
            icon = painterResource(id = R.drawable.ic_home),
        ),

        MyBottomNavItem(
            route = Screen.CategoryScreen.route,
            icon = painterResource(id = R.drawable.ic_cake),
        ),

        MyBottomNavItem(
            route = Screen.BasketScreen.route,
            icon = painterResource(id = R.drawable.ic_shopping_cart),
        ),

        MyBottomNavItem(
            route = Screen.PastryScreen.route,
            icon = painterResource(id = R.drawable.ic_pastry),
        ),
        MyBottomNavItem(
            route = Screen.ProfileScreen.route,
            icon = painterResource(id = R.drawable.ic_user),
        ),
    )

    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in item.map { it.route }


    AnimatedVisibility(
        visible = showBottomBar && CHECKED_LOGIN,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(55.dp)
        ) {
            HorizontalDivider(
                thickness = 1.3.dp,
                color = Color.LightGray.copy(0.6f)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp),
                contentAlignment = Alignment.Center
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_polygon),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)

                    )
                }
                Box(contentAlignment = Alignment.TopCenter) {
                        IconButton(onClick = {
                            if (backStackEntry.value?.destination?.route!=Screen.BasketScreen.route){
                                navHostController
                                    .navigate(Screen.BasketScreen.route){
                                        popUpTo(0){
                                            inclusive =true
                                        }
                                    }
                            }

                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shopping_cart),
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                item.forEachIndexed { index, item ->
                    val selected = item.route == backStackEntry.value?.destination?.route
                    if (index != 2) {
                        Box(
                            modifier = Modifier.fillMaxHeight()
                                .size(70.dp,60.dp),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Row {
                                    NavigationBarItem(
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = Color.Black,
                                            unselectedIconColor = Color.DarkGray,
                                            indicatorColor = Color.White
                                        ),
                                        selected = selected,
                                        onClick = {
                                            if (!selected){
                                                navHostController.navigate(item.route){ popUpTo(0){ inclusive =true } }
                                            }
                                             },
                                        icon = {
                                            Icon(
                                                painter = item.icon,
                                                contentDescription = "",
                                                modifier = Modifier.size(32.dp)
                                            )
                                        })
                                }
                            }
                            if (selected) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.TopCenter
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(id = R.drawable.back_item_bottom_nav),
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                            }

                        }
                    }
                    else {
                       Spacer(modifier = Modifier.padding(horizontal = 14.dp))
                    }


                }


            }
        }
    }

}
