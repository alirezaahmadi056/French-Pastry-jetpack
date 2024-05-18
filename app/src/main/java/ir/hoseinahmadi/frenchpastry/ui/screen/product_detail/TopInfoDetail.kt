package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopInfoDetail(
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.AutoMirrored.Rounded.Message,
            contentDescription = "",
            Modifier.padding(5.dp).size(25.dp)

        )
        VerticalDivider(
            modifier = Modifier.height(16.dp),
            color = Color.LightGray,
            thickness = 0.5.dp
        )

        Icon(
            Icons.Outlined.Share,
            contentDescription = "",
            Modifier.padding(5.dp).size(25.dp)
        )

               VerticalDivider(
            modifier = Modifier.height(16.dp),
            color = Color.LightGray,
            thickness = 0.5.dp
        )

        Icon(
            Icons.Rounded.BookmarkBorder,
            contentDescription = "",
            Modifier.padding(5.dp).size(25.dp)
        )

          VerticalDivider(
            modifier = Modifier.height(16.dp),
            color = Color.LightGray,
            thickness = 0.5.dp
        )

        Icon(
            Icons.Rounded.Star,
            contentDescription = "",
            Modifier.padding(5.dp).size(25.dp)
        )


    }

}