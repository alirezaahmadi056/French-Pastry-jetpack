package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.h5
import ir.hoseinahmadi.frenchpastry.util.Constants

var showSetComment = mutableStateOf(false)

@Composable
fun ProductSetCommentSection(
    navHostController: NavHostController
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        onClick = {
            if (Constants.USER_NAME!=""){
                showSetComment.value = true
            }else{
                navHostController.navigate(Screen.ProfileInfoScreen.route)
            }

        },
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 7.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = "",
                Modifier.size(20.dp)
            )

            Text(
                text = "دیدگاه خود را درباره این محصول بنویسید",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp),
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5
            )

            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
            )

        }

    }


}