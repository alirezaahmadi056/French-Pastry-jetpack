package ir.hoseinahmadi.frenchpastry.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.ui.theme.font_bold
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.ui.theme.h3

@Composable
fun TopProductHeader(
    title: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 15.dp,
                bottom = 5.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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

        val context = LocalContext.current
        TextButton(onClick = {
            onClick()
            Toast.makeText(context, "محصولی برای نمایش وجود ندراد !", Toast.LENGTH_SHORT).show()
        }) {
            Text(
                text = "بیشتر",
                color = Color.Black,
                style = MaterialTheme.typography.h3
            )
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription ="",
                tint = Color.Black
                )
        }

    }
}