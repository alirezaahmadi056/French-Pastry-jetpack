package ir.hoseinahmadi.frenchpastry.ui.screen.address

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.gson.Gson
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.data.model.addres.Addresse
import ir.hoseinahmadi.frenchpastry.ui.screen.product_detail.Header
import ir.hoseinahmadi.frenchpastry.ui.theme.LightCyan
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.grayCategory
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.InputValidation
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.AddressViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AddAddressScreen(
    data: String?,
    navHostController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel()
) {

    var title = "ثبت آدرس جدید"
    var n = ""
    var p = ""
    var a = ""

    val item = Gson().fromJson(data, Addresse::class.java)

    if (data != null) {
        title = "ویرایش آدرس"
        n = item.receiver
        p = item.phone
        a = item.address
    }

    var name by remember {
        mutableStateOf(n)
    }
    var phone by remember {
        mutableStateOf(p)
    }
    var address by remember {
        mutableStateOf(a)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 5.dp,
                        vertical = 8.dp
                    ),
                horizontalArrangement = Arrangement.Start,
            ) {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }


        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())

        ) {
            Header(title = title)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 5.dp)
            ) {
                var check by remember {
                    mutableStateOf(false)
                }

                Text(
                    text = "تنها محدوده استان خراسان جنوبی- شهر بیرجند پذیرفته می شود.",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))

                GlideImage(
                    modifier = Modifier.fillMaxWidth().height(350.dp),
                    model = "https://raw.githubusercontent.com/ihoseinam/video-shop/main/map_img.jpg",
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                ){
                    it.placeholder(R.drawable.img_place_holder)
                }

                Spacer(modifier = Modifier.height(10.dp))
                TextFieldAddAddress(
                    value = name,
                    onChangeValue = { name = it },
                    hint = "نام و نام خانوادگی گیرنده",
                    isError = check && name.length < 8
                )

                TextFieldAddAddress(
                    value = PastryHelper.pastryByLocate(phone),
                    onChangeValue = { phone = it },
                    hint = "شماره همراه گیرنده",
                    isError = check && phone.length != 11,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textDirection = TextDirection.Ltr
                )

                TextFieldAddAddress(
                    value = address,
                    onChangeValue = { address = it },
                    hint = "خیابان- کوچه-پلاک",
                    isError = check && address.length < 20,
                    singleLine = false
                )

                val context = LocalContext.current

                Button(
                    shape = RoundedCornerShape(9.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = {
                        check = true
                        if (name.length < 8) {
                            Toast.makeText(context, "نام را ماکل تر وارد کنید", Toast.LENGTH_SHORT)
                                .show()
                        } else if (address.length < 20) {
                            Toast.makeText(context, "آدرس را کامل تر وارد کنید", Toast.LENGTH_SHORT)
                                .show()
                        } else if (phone.isNotEmpty() && phone.length == 11) {
                            if (data == null) {
                                viewModel.addAddress(
                                    context = context,
                                    phone = phone,
                                    address = address,
                                    receiver = name
                                )
                                Toast.makeText(
                                    context,
                                    "آدرس با موفقیت افزوده شد",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                viewModel.editAddress(
                                    context = context,
                                    id = item.ID,
                                    phone = phone,
                                    address = address,
                                    receiver = name
                                )
                                Toast.makeText(
                                    context,
                                    "آدرس با موفقیت ویرایش شد",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            navHostController.popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "فرمت شماره تلفن اشتباه می باشد",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    })
                {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                }


            }
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
        }
    }

}


@Composable
private fun TextFieldAddAddress(
    value: String,
    onChangeValue: (it: String) -> Unit,
    hint: String,
    isError: Boolean,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    textDirection: TextDirection = TextDirection.Rtl
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        value = value,
        textStyle = MaterialTheme.typography.body1.copy(
            textDirection = textDirection
        ),
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = hint,
                color = Color.DarkGray,
                style = MaterialTheme.typography.body1

            )
        },
        isError = isError,
        onValueChange = { onChangeValue(it) },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.darkText,
            focusedContainerColor = MaterialTheme.colorScheme.grayCategory,
            unfocusedContainerColor = MaterialTheme.colorScheme.grayCategory,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.LightCyan,
            focusedIndicatorColor = MaterialTheme.colorScheme.LightCyan,
            errorTextColor = Color.Red,
        ),
        singleLine = singleLine
    )
}