package ir.hoseinahmadi.frenchpastry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.hoseinahmadi.frenchpastry.navigation.BottomNavigationBar
import ir.hoseinahmadi.frenchpastry.navigation.SetUpNavGraph
import ir.hoseinahmadi.frenchpastry.ui.component.AppConfig
import ir.hoseinahmadi.frenchpastry.ui.component.ChangeStatusBarColor
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppConfig()
            navHostController = rememberNavController()
            ChangeStatusBarColor(navHostController)

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                ModalNavigationDrawer(
                    gesturesEnabled = drawerState.isOpen,
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerContent(
                            navHostController,
                            onClick = { scope.launch { drawerState.close() } })
                    }) {
                    Scaffold(
                        containerColor = Color(0xffF4F6FF),
                        topBar = {
                            MyTopBar(navHostController) {
                                scope.launch {
                                   drawerState.open()
                                }
                            }
                        },
                        bottomBar = {
                            BottomNavigationBar(navHostController)
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                        ) {
                            SetUpNavGraph(navHostController)


                        }

                    }
                }


            }


        }
    }
}

