package ir.hoseinahmadi.frenchpastry.ui.screen.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.data.model.home.PastryItem

@Composable
fun NewProductSection(
    navHostController: NavHostController,
    item:List<PastryItem>
){
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        item { Spacer(modifier =Modifier.width(3.dp)) }
        items(item){
            HomeItemCard(navHostController, it) }
    }
}
