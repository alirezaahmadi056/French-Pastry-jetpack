package ir.hoseinahmadi.frenchpastry.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun BasketItem(
    onClick: () -> Unit,
    painter: Painter,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .size(90.dp, 120.dp)
            .padding(3.dp),
        onClick = { onClick() })
    {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription ="" )
    }
}