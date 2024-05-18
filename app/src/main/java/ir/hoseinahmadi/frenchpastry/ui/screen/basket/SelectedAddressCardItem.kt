package ir.hoseinahmadi.frenchpastry.ui.screen.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.hoseinahmadi.frenchpastry.data.model.addres.Addresse
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.util.PastryHelper

var selectedIndex = mutableIntStateOf(0)
@Composable
fun SelectedAddressCardItem(
    item: Addresse,
    index: Int
) {


    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 5.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xff24A751),
                    unselectedColor = Color.DarkGray
                ),
                selected = selectedIndex.intValue==index,
                onClick = { selectedIndex.intValue = index })
            Text(
                text = "آدرس ${PastryHelper.pastryByLocate((index+1).toString())}:",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
            )

        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = "گیرنده: ${item.receiver}  ",
            style = MaterialTheme.typography.body1,
            color = Color.Black
        )

        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = "شماره همراه:  ${PastryHelper.pastryByLocate(item.phone)}",
            style = MaterialTheme.typography.body1,
            color = Color.Black
        )

        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            text = "آدرس:  ${item.address}",
            style = MaterialTheme.typography.body1,
            color = Color.Black
        )
    }

}