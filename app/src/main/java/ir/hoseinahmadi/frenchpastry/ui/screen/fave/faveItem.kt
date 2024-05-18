package ir.hoseinahmadi.frenchpastry.ui.screen.fave

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.db.entites.FaveEntities
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.FaveViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FaveItemCard(item: FaveEntities, viewModel: FaveViewModel) {
val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(modifier = Modifier.size(30.dp), onClick = {

                viewModel.removeFaveItem(item)
                Toast.makeText(context, "حذف شد", Toast.LENGTH_SHORT).show()
            }
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "",
                    tint = Color.DarkGray
                )
            }

            GlideImage(
                modifier = Modifier
                    .size(148.dp, 93.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(15.dp)),
                model = item.imgAddress,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.padding(horizontal = 9.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.h2,
                    color = Color.Black
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, top = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PastryHelper.pastryByLocateAndSeparator(item.salePrice.toString()),
                        style = MaterialTheme.typography.body1,
                        color = Color(0xff24A751)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        Modifier.size(20.dp),
                        tint = Color.Black
                    )
                }


            }

        }

    }
}
