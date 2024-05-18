package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.BookmarkAdded
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.db.entites.FaveEntities
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.FaveViewModel

@Composable
fun TopBarDetail(
    navHostController: NavHostController,
    item: FaveEntities,
    viewModel: FaveViewModel = hiltViewModel()
) {

    val isBookmarked by viewModel.isHasBookmark(item.id).collectAsState(initial = false)
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 4.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    Icons.Rounded.ArrowForward,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.size(26.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.black_logo),
                contentDescription = "",
                Modifier.padding(start = 27.dp),
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconToggleButton(checked = isBookmarked,
                    onCheckedChange = {
                        if (!it) {
                            viewModel.removeFaveItem(item)
                            Toast.makeText(context, "از لیست علاقه مندی حذف شد", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.addFaveItem(item)
                            Toast.makeText(context, "به لیست علاقه مندی اضافه شد", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Rounded.BookmarkAdded else Icons.Filled.BookmarkBorder,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }


                IconButton(onClick = {
                    shareToSocialMedia(
                        context,
                        item.name,
                        item.salePrice.toString(),
                        "https://hoseinahmadi.ir"
                    )
                }) {
                    Icon(
                        Icons.Rounded.Share,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(25.dp)

                    )
                }


            }

        }
        HorizontalDivider(
            thickness = 1.4.dp,
            color = Color.LightGray.copy(0.6f)
        )
    }

}
private fun shareToSocialMedia(
    context: Context,
    productName: String,
    productPrice: String,
    url: String
) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "$productName با قیمت باورنکردنی ${PastryHelper.pastryByLocateAndSeparator(productPrice)} تومان فقط در شیرینی فرانسوی \n $url"
    )

    context.startActivity(Intent.createChooser(shareIntent, "اشتراک گذاری"))

}