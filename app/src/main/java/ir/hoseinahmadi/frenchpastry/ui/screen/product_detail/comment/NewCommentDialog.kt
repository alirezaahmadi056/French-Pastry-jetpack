package ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.comment

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.Header
import ir.hoseinahmadi.frenchpastry.ui.theme.LightCyan
import ir.hoseinahmadi.frenchpastry.ui.theme.amber
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.grayAlpha
import ir.hoseinahmadi.frenchpastry.ui.theme.grayCategory
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.ui.theme.h3
import ir.hoseinahmadi.frenchpastry.ui.theme.h4
import ir.hoseinahmadi.frenchpastry.util.Constants
import ir.hoseinahmadi.frenchpastry.viewModel.ProductDetailViewModel
import ir.hoseinahmadi.frenchpastry.wrapper.DeviceInfo
import ir.hoseinahmadi.mydigikala.ui.component.Loading3Dots
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCommentDialog(
    productId: Int,
) {
    val show by remember {
        showSetComment
    }

    if (show) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = { showSetComment.value = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(bottom = 40.dp)
            ) {
                CommentForm(productId)
            }
        }
    }


}


@Composable
fun CommentForm(
    productId: Int,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        viewModel.resultSendComment.collectLatest {
            if (it) {
                showSetComment.value = false
                viewModel.getProductById(productId)
            }
        }
    }

    var sliderValue by remember {
        mutableFloatStateOf(1f)
    }
    val score = when (sliderValue.toInt()) {
        1 -> {
            "بدون امتیاز!"
        }

        2 -> {
            "خیلی بد"
        }

        3 -> {
            "بد"
        }

        4 -> {
            "معمولی"
        }

        5 -> {
            "خوب"
        }

        6 -> {
            "عالی"
        }

        else -> {
            ""
        }
    }
Header(title = "ثبت نظر")
    Text(
        text = score,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        style = MaterialTheme.typography.h2,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.darkText,
        textAlign = TextAlign.Center
    )

    Slider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 10.dp
            ),
        value = sliderValue,
        onValueChange = { sliderValue = it },
        onValueChangeFinished = {
        },
        valueRange = 1f..6f,
        steps = 4,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.amber,
            activeTickColor = MaterialTheme.colorScheme.amber,
            inactiveTickColor = MaterialTheme.colorScheme.grayAlpha,
            activeTrackColor = MaterialTheme.colorScheme.amber,
            inactiveTrackColor = MaterialTheme.colorScheme.grayCategory
        )

    )


///////////////////////////////////////////////////////////////////////////////////////////////////
    var commentBody by remember { mutableStateOf("") }

    var loadin by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {


        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = commentBody,
            textStyle = MaterialTheme.typography.body1,
            placeholder = {
                Text(
                    text = "نظر خود را وارد کنید",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.body1

                )
            },
            onValueChange = { commentBody = it },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.darkText,
                focusedContainerColor = MaterialTheme.colorScheme.grayCategory,
                unfocusedContainerColor = MaterialTheme.colorScheme.grayCategory,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.LightCyan,
                focusedIndicatorColor = MaterialTheme.colorScheme.LightCyan,
            ),
            maxLines = 3,
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 6.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.grayCategory
        )


        val context = LocalContext.current

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 16.dp,
                ),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                loadin = true
                if (commentBody.length <=15) {
                    loadin = false
                    Toast.makeText(
                        context,
                        "نظر را کامل تر وارد کنید",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sliderValue.toInt() - 1 == 0) {
                    loadin = false
                    Toast.makeText(
                        context,
                        "امتیاز را وارد کنید",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    viewModel.setNewComment(
                        apiKey = Constants.API_KEY,
                        deviceUid = DeviceInfo.getDeviceID(context),
                        publicKey = DeviceInfo.getPublicKey(context),
                        postId = productId,
                        content = commentBody,
                        rate = sliderValue.toInt() - 1,
                    )
                }


            },
        ) {
            AnimatedVisibility(visible = loadin) {
                Loading3Dots(isDark = false)
            }
            AnimatedVisibility(visible = !loadin) {
                Text(
                    text = "ثبت نظر",
                    modifier = Modifier
                        .padding(
                            vertical = 4.dp,
                        ),
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                )
            }

        }


    }


}

