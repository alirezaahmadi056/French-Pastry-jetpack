package ir.hoseinahmadi.frenchpastry.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.ui.theme.body1

@Composable
fun PastryScreen(navHostController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xffF4F6FF)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.ErrorOutline,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier.size(50.dp)
        )
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 10.dp),
            text = "متاسفانه این بخش هنوز تکمیل نشده (سمت سرور)",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center

        )

    }
}