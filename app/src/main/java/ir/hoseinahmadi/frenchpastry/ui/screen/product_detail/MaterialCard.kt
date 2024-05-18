package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.hoseinahmadi.frenchpastry.data.model.product_detail.Material
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.h4
import ir.hoseinahmadi.frenchpastry.ui.theme.h6

@Composable
fun MaterialCard(item:Material){
Column() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier.padding(8.dp),
            text = item.material,
            style = MaterialTheme.typography.body1,
            color = Color.Black,
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = item.amount,
            style = MaterialTheme.typography.body2,
            color = Color.DarkGray
        )
        
    }

    HorizontalDivider(
        thickness = 0.8.dp,
        color = Color.LightGray.copy(0.4f)
    )
}
    


}