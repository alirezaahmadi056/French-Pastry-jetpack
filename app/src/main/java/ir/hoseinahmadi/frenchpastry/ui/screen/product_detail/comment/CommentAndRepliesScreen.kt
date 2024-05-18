package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.model.product_detail.Comment
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.PastryHelper

@Composable
fun CommentAndRepliesScreen(
    navHostController: NavHostController,
    data: String
) {
    val item = Gson().fromJson(data, Comment::class.java)

    Scaffold(
        topBar = { Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            Icons.Rounded.ArrowForward,
                            contentDescription = "",
                            Modifier
                                .padding(horizontal = 5.dp)
                                .size(20.dp),
                            tint = Color.Black
                        )
                    }
                    Text(
                        text = "پاسخ ها",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )


                }

                HorizontalDivider(
                    thickness = 1.5.dp,
                    color = Color.LightGray.copy(0.4f)
                )
            } }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .padding(it)
                .padding(4.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.h6,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.body,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                HorizontalDivider(
                    Modifier.padding(top = 5.dp),
                    thickness = 1.dp,
                    color = Color.LightGray.copy(0.4f)

                )

            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = item.replies[0].name,
                    style = MaterialTheme.typography.h6,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.replies[0].body,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                HorizontalDivider(
                    Modifier.padding(top = 5.dp),
                    thickness = 1.dp,
                    color = Color.LightGray.copy(0.4f)

                )

            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = item.replies[0].replies[0].name,
                    style = MaterialTheme.typography.h6,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.replies[0].replies[0].body,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                HorizontalDivider(
                    Modifier.padding(top = 5.dp),
                    thickness = 1.dp,
                    color = Color.LightGray.copy(0.4f)

                )

            }

            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = item.replies[1].name,
                    style = MaterialTheme.typography.h6,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.replies[1].body,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                HorizontalDivider(
                    Modifier.padding(top = 5.dp),
                    thickness = 1.dp,
                    color = Color.LightGray.copy(0.4f)

                )

            }


        }
    }




}