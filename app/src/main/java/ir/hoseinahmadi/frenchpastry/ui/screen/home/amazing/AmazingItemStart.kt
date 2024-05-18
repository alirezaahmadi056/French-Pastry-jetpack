package ir.hoseinahmadi.frenchpastry.ui.screen.home.amazing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.ui.theme.font_bold
import ir.hoseinahmadi.frenchpastry.ui.theme.h1
import ir.hoseinahmadi.frenchpastry.ui.theme.h3
import ir.hoseinahmadi.frenchpastry.ui.theme.h5

@Composable
fun AmazingItemStart() {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, end = 2.dp)
            .width(170.dp)
            .height(230.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(id = R.drawable.ic_amazingstart),
                contentDescription = "",
                Modifier.size(160.dp, 190.dp),
                contentScale = ContentScale.FillBounds
            )
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Column(
                modifier = Modifier.width(150.dp)
                    .padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(80.dp))
                Text(
                    textAlign = TextAlign.Center,
                    text = "پـیشنهاد",
                    style = MaterialTheme.typography.h1,
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    fontFamily = font_bold

                    )
                Text(
                    textAlign = TextAlign.Center,
                    text = "ویــژه",
                    style = MaterialTheme.typography.h1,
                    color = Color(0xFFFFC500),
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    fontFamily = font_bold

                    )


            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
                TextButton(
                    modifier = Modifier.padding(start = 7.dp),
                    onClick = { }) {
                    Text(
                        text = "بیشتر",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h5
                    )
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(17.dp)
                    )


            }
        }

    }


}