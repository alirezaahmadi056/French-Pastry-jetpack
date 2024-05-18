package ir.hoseinahmadi.frenchpastry.navigation

sealed class Screen(val route: String) {


    data object SplashScreen : Screen("Splash_Screen")

    data object HomeScreen : Screen("Home_Screen")
    data object LoginScreen : Screen("Login_Screen")
    data object BasketScreen : Screen("Basket_Screen")
    data object ProfileScreen : Screen("Profile_Screen")
    data object CategoryScreen : Screen("Category_Screen")
    data object ProductDetailScreen : Screen("ProductDetail_Screen")
    data object ProfileInfoScreen : Screen("ProfileInfoScreen")
    data object CommentAndReplies : Screen("CommentAndReplies")
    data object PastryScreen : Screen("PastryScreen")
    data object AllAddressScreen : Screen("AllAddressScreen")
    data object AddAddressScreen : Screen("AddAddressScreen")
    data object FaveScreen : Screen("FaveScreen")

}