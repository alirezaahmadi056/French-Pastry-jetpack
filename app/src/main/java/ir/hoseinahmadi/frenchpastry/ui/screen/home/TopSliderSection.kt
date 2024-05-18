package ir.hoseinahmadi.frenchpastry.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ir.hoseinahmadi.frenchpastry.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun TopSliderSection(
    sliderList: List<String>
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffF0F3FF))
            .height(220.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            val pagerState = rememberPagerState()
            var imageUrl by remember {
                mutableStateOf("")
            }
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth(),
                count = sliderList.size,
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = 5.dp,
                )
            ) { index ->
                imageUrl = sliderList[index]
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GlideImage(
                        model = imageUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    ){
                        it.placeholder(R.drawable.img_place_holder)
                    }
                }

            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier
                        .size(80.dp, 25.dp)
                        .clip(
                            RoundedCornerShape(12.dp)
                        )
                        .background(Color(0xffF0F3FF)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        activeColor = Color.Black,
                        inactiveColor = Color(0xffD9D9D9),
                        indicatorWidth = 8.dp,
                        indicatorHeight = 8.dp,
                        indicatorShape = CircleShape
                    )
                }


            }

            LaunchedEffect(pagerState.currentPage) {
                delay(4000)
                var newPosition = pagerState.currentPage + 1
                if (newPosition > sliderList.size - 1) newPosition = 0
                pagerState.scrollToPage(newPosition)

            }
        }
    }
}




