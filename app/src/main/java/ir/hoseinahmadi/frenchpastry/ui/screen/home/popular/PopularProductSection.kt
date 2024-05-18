package ir.hoseinahmadi.frenchpastry.ui.screen.home.popular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.data.model.home.PastryItem
import ir.hoseinahmadi.frenchpastry.ui.screen.home.amazing.AmazingItem
import ir.hoseinahmadi.frenchpastry.ui.screen.home.popular.PopularItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularProductSection(
    navHostController: NavHostController,
    item: List<PastryItem>
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        maxItemsInEachRow = 2,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (it in item){
            PopularItem(navHostController = navHostController, item = it)
        }
    }

}