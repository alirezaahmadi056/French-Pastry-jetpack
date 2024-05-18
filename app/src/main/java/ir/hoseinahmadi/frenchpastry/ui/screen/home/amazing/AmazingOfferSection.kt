package ir.hoseinahmadi.frenchpastry.ui.screen.home.amazing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.model.home.PastryItem
import ir.hoseinahmadi.frenchpastry.ui.screen.home.HomeItemCard

@Composable
fun AmazingOfferSection(
    navHostController: NavHostController,
    item: List<PastryItem>
) {
    Spacer(modifier = Modifier.height(10.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .background(Color(0xff532379))
    ) {
        item {
            AmazingItemStart()
        }
        items(item){
            AmazingItem(navHostController,it)
        }
        item { AmazingItemShowMore() }

    }

}