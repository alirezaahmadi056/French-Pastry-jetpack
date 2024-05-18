package ir.hoseinahmadi.frenchpastry.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ir.hoseinahmadi.frenchpastry.navigation.Screen

@Composable
fun ChangeStatusBarColor(navHostController: NavHostController) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()

    when (navBackStackEntry?.destination?.route) {
        Screen.SplashScreen.route -> {
            SideEffect {
                systemUiController.setStatusBarColor(color = Color.Black)
            }
        }

        else -> {
            SideEffect {
                systemUiController.setStatusBarColor(color = Color.White)
            }
        }
    }

}