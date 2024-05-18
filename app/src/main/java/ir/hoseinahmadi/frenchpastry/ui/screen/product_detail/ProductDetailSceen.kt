package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.db.entites.FaveEntities
import ir.hoseinahmadi.frenchpastry.data.model.product_detail.ProductResponse
import ir.hoseinahmadi.frenchpastry.ui.screen.home.TopSliderSection
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.comment.NewCommentDialog
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.comment.ProductSetCommentSection
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.comment.TextCommentCard
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.font_bold
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.ui.theme.h4
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.ProductDetailViewModel
import ir.hoseinahmadi.mydigikala.ui.component.OurLoading
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ProductDetailScreen(
    navHostController: NavHostController,
    productId: Int,
    productDetailViewModel: ProductDetailViewModel = hiltViewModel()
) {
    ProductScreen(
        navHostController,
        productId,
        productDetailViewModel,
    )
}


@Composable
private fun ProductScreen(
    navHostController: NavHostController,
    productId: Int,
    productDetailViewModel: ProductDetailViewModel,
) {

    var pastryItem by remember {
        mutableStateOf(ProductResponse())
    }

    var loading by remember {
        mutableStateOf(true)
    }



    var imgSlider by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    val fakeImg = listOf(
            "https://raw.githubusercontent.com/ihoseinam/video-shop/main/slider1.png",
            "https://raw.githubusercontent.com/ihoseinam/video-shop/main/slider1.png",
        )



    LaunchedEffect(productId) {
        launch {
            productDetailViewModel.getProductById(productId)
            productDetailViewModel.productItem.collectLatest {
                if (it.http_code == 200 && it.pastry != null) {
                    pastryItem = it
                    imgSlider = it.pastry.gallery ?: fakeImg
                    delay(500)
                    loading = false
                }
            }
        }
    }

    var img = imgSlider
    if (img.isEmpty() ||img==null) {
        img= listOf(
            "https://raw.githubusercontent.com/ihoseinam/video-shop/main/slider1.png",
            "https://raw.githubusercontent.com/ihoseinam/video-shop/main/slider1.png",
        )

    }
    val config = LocalConfiguration.current
    if (loading) {
        OurLoading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        AddOrderBottomSheet(pastryItem.pastry!!)
        Scaffold(
            bottomBar = {
                    BottomBarHome(
                        pastryItem.pastry!!,
                        navHostController,
                    )
            },
            topBar = {
                    TopBarDetail(
                        navHostController = navHostController,
                        FaveEntities(
                            id = pastryItem.pastry?.ID ?: 34,
                            name = pastryItem.pastry?.title ?: "",
                            imgAddress = img[0],
                            salePrice = pastryItem.pastry?.sale_price ?: 0
                        )
                    )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xffF0F3FF))
                    .padding(it)
            ) {
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item { Header(pastryItem.pastry!!.title) }
                item { TopSliderSection(img) }
                item { Header("مواد بکار رفته در شیرینی") }
                items(pastryItem.pastry!!.materials) {
                    MaterialCard(item = it)
                }
                item { Header("توضیحات") }

                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = "شیرینی نخودچی با آرد ۱۰۰% نخودچی خالص و روغن حیوانی اصل، شیرینی برنجی از برنج معطر آسیاب شده شمال ایران همراه گلاب کاشان، ملکه بادام از خمیر کره ای با عطر هل همراه لایه ای از مخلوط عسل کوهپایه الوند، زعفران و خلال بادام، پسته و زرشک با خمیر کره ای همراه پسته کرمان و زرشک پفکی بی دانه خراسان",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colorScheme.darkText
                    )
                }
                item { Header("ثبت نظر") }
                item { ProductSetCommentSection(navHostController) }
                item { NewCommentDialog(productId) }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp,
                                vertical = 15.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_header),
                                contentDescription = "",
                                Modifier.size(35.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "نظرات کاربران",
                                color = Color(0xff101219),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.h2,
                                fontFamily = font_bold,
                                fontSize = 22.sp,
                            )
                        }
                        Text(
                            text = "${PastryHelper.pastryByLocate(pastryItem.pastry!!.comment_count.toString())} ${"نظر"}",
                            color = Color.Black,
                            style = MaterialTheme.typography.h4,
                        )

                    }

                }
                if (pastryItem.pastry!!.comments != null) {
                    items(pastryItem.pastry!!.comments) {
                        TextCommentCard(navHostController, item = it)
                    }
                } else {
                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            text = "نظری برای نمایش وجود ندارد",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1,
                            color = Color.Black,
                        )
                    }
                }

                item { Spacer(modifier = Modifier.height(20.dp)) }
            }
        }
    }

}


@Composable
fun Header(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_header),
            contentDescription = "",
            Modifier.size(38.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            color = Color(0xff101219),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h2,
            fontFamily = font_bold,
            fontSize = 22.sp,
        )
    }

}