package ir.hoseinahmadi.frenchpastry.ui.screen.address

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.model.addres.Addresse
import ir.hoseinahmadi.frenchpastry.navigation.Screen
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.comment.extractTime
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.comment.persianDate
import ir.hoseinahmadi.frenchpastry.ui.theme.LightCyan
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.h5
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.AddressViewModel

@Composable
fun AddressCardItem(
    navHostController: NavHostController,
    item: Addresse,
    viewModel: AddressViewModel = hiltViewModel()
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(top = 4.dp)
                .background(Color(0xffF4F6FF))
        ) {
            val context = LocalContext.current
            Icon(
                painter = painterResource(id = R.drawable.ic_edite),
                contentDescription = "",
                modifier = Modifier
                    .size(33.dp)
                    .clickable {
                        val items = Gson().toJson(item)
                        navHostController.navigate(Screen.AddAddressScreen.route + "?data=$items")
                    },
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.height(6.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "",
                modifier = Modifier
                    .size(33.dp)
                    .clickable {
                        viewModel.deleteAddress(context, id = item.ID)
                        Toast
                            .makeText(context, "آدرس حذف شد", Toast.LENGTH_SHORT)
                            .show()
                    },
                tint = Color(0xffCF3C3C)
            )


        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f)
                .padding(end = 15.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                text = "گیرنده: ${item.receiver}  ",
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )

            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                text = "شماره همراه:  ${PastryHelper.pastryByLocate(item.phone)}",
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )

            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                text = "آدرس:  ${item.address}",
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 13.dp, bottom = 10.dp, top = 4.dp),
                text = PastryHelper.pastryByLocate("${extractTime(item.updated_at)} - ${persianDate(item.updated_at)}"),
                style = MaterialTheme.typography.h5,
                color = Color.DarkGray,
                textAlign = TextAlign.End
            )

        }
    }


}