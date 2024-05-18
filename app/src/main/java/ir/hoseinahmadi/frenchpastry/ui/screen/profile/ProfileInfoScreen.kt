package ir.hoseinahmadi.frenchpastry.ui.screen.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gmail.hamedvakhide.compose_jalali_datepicker.JalaliDatePickerDialog
import ir.hoseinahmadi.frenchpastry.R
import ir.hoseinahmadi.frenchpastry.ui.theme.LightCyan
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.darkText
import ir.hoseinahmadi.frenchpastry.ui.theme.font_bold
import ir.hoseinahmadi.frenchpastry.ui.theme.grayCategory
import ir.hoseinahmadi.frenchpastry.ui.theme.h2
import ir.hoseinahmadi.frenchpastry.ui.theme.h4
import ir.hoseinahmadi.frenchpastry.ui.theme.h6
import ir.hoseinahmadi.frenchpastry.util.Constants
import ir.hoseinahmadi.frenchpastry.util.PastryHelper
import ir.hoseinahmadi.frenchpastry.viewModel.DatStoreViewModel
import ir.hoseinahmadi.frenchpastry.viewModel.UserInfoViewModel
import ir.hoseinahmadi.frenchpastry.wrapper.DeviceInfo
import ir.hoseinahmadi.mydigikala.ui.component.Loading3Dots
import ir.hoseinahmadi.mydigikala.ui.component.OurLoading
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ProfileInfoScreen(
    navHostController: NavHostController,
    infoViewModel: UserInfoViewModel = hiltViewModel(),
    datStoreViewModel: DatStoreViewModel = hiltViewModel()
) {
    var userName by remember {
        mutableStateOf("")
    }
    var userPhone by remember {
        mutableStateOf(Constants.USER_PHONE)
    }
    var userDate by remember {
        mutableStateOf("")
    }
    var userEmail by remember {
        mutableStateOf("")
    }

    var userNationalCode by remember {
        mutableStateOf("")
    }

    var checkinput by remember {
        mutableStateOf(false)
    }

    var loading by remember {
        mutableStateOf(false)
    }
    var sex by remember {
        mutableIntStateOf(0)
    }
    val serverLoading by infoViewModel.loading.collectAsState(initial = false)

    val context = LocalContext.current
    LaunchedEffect(true) {
        launch {
            infoViewModel.getUserInfo(context = context)
        }
        launch {
            infoViewModel.userInfo.collectLatest { userResponse ->
                if (userResponse.http_code == 200) {
                    val name = userResponse.user!!.fullname
                    userName = name
                    Constants.USER_NAME = name
                    Constants.USER_PHONE = userResponse.user.phone
                    datStoreViewModel.saveUserName(name)
                    userDate = userResponse.user.birthdate
                    userEmail = userResponse.user.email
                    userPhone = userResponse.user.phone
                    sex = userResponse.user.sex
                    loading = false
                }
            }
        }
        launch {
            infoViewModel.userSendInfo.collectLatest { userResponse ->
                if (userResponse.http_code == 200) {
                    val name = userResponse.user!!.fullname
                    datStoreViewModel.saveUserName(name)
                    Constants.USER_NAME = name
                    navHostController.popBackStack()
                    loading = false
                }
            }
        }
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

        val config = LocalConfiguration.current
        if (serverLoading) {
            OurLoading(height = config.screenHeightDp.dp, isDark = true)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xffF0F3FF))
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_f),
                        contentDescription = "",
                        Modifier.size(38.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "اطلاعات کاربری",
                        color = Color(0xff101219),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h2,
                        fontFamily = font_bold,
                        fontSize = 22.sp,
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(17.dp))
                        .background(Color.White)
                        .padding(horizontal = 15.dp, vertical = 8.dp)
                ) {


                    TextFieldProfileInfo(
                        value = userName,
                        onChangeValue = { userName = it },
                        icon = Icons.Rounded.Edit,
                        hint = "نام و نام خانوادگی خود را وارد کنید",
                        isError = checkinput && userName.isEmpty()
                    )

                    TextFieldProfileInfo(
                        value = userPhone,
                        onChangeValue = { },
                        icon = Icons.Rounded.Phone,
                        hint = "",
                        textDirection = TextDirection.Ltr,
                        isError = checkinput && userPhone.isEmpty()
                    )

                    //date
                    val openDialog = remember { mutableStateOf(false) }
                    JalaliDatePickerDialog(
                        openDialog = openDialog,
                        onSelectDay = { //it:JalaliCalendar

                        },
                        onConfirm = {
                            val date = "${it.year}/${it.month}/${it.day}"
                            userDate = date
                        }
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        value = PastryHelper.pastryByLocate(userDate),
                        textStyle = MaterialTheme.typography.body1.copy(
                            textDirection = TextDirection.Ltr
                        ),
                        placeholder = {
                            Text(
                                text = "تاریخ تولد خود را وارید",
                                color = Color.DarkGray,
                                style = MaterialTheme.typography.h6

                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { openDialog.value = true }) {
                                Icon(
                                    Icons.Rounded.CalendarMonth,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }

                        },
                        isError = userDate.isEmpty() && checkinput,
                        onValueChange = { },
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
                        singleLine = true
                    )



                    TextFieldProfileInfo(
                        value = userEmail,
                        onChangeValue = { userEmail = it },
                        icon = Icons.Rounded.Email,
                        hint = "ایمیل خود را وارد کنید",
                        textDirection = TextDirection.Ltr,
                        isError = checkinput && userEmail.isEmpty(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    TextFieldProfileInfo(
                        value = userNationalCode,
                        onChangeValue = {
                            userNationalCode = it
                        },
                        icon = Icons.Rounded.Edit,
                        hint = "کد ملی خود را وارد کنید",
                        textDirection = TextDirection.Ltr,
                        isError = checkinput && userNationalCode.length != 10,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "جنسیت:",
                            style = MaterialTheme.typography.h4,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = sex == 0,
                                onClick = { sex = 0 })

                            Text(
                                text = "مرد",
                                style = MaterialTheme.typography.body1,
                                color = Color.Black
                            )

                        }
                        Spacer(modifier = Modifier.width(5.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = sex == 1,
                                onClick = { sex = 1 })
                            Text(
                                text = "زن",
                                style = MaterialTheme.typography.body1,
                                color = Color.Black
                            )

                        }

                    }

                    Button(
                        shape = RoundedCornerShape(9.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 5.dp,
                                vertical = 9.dp
                            ),
                        onClick = {
                            checkinput = true
                            if (userName.isNotEmpty()
                                && userDate.isNotEmpty()
                                && userPhone.isNotEmpty()
                                && userEmail.isNotEmpty()
                                && userNationalCode.length == 10
                            ) {
                                loading = true
                                checkinput = false

                                infoViewModel.sendUserInfo(
                                    deviceUid = DeviceInfo.getDeviceID(context),
                                    publicKey = DeviceInfo.getPublicKey(context),
                                    apiKey = Constants.API_KEY,
                                    fullName = userName,
                                    nationalCode = userNationalCode,
                                    email = userEmail,
                                    sex = sex.toString(),
                                    birthdate = userDate,
                                )
                                Constants.USER_NAME = userName
                            }
                        }) {
                        AnimatedVisibility(visible = !loading) {
                            Text(
                                text = "ثبت اطلاعات",
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        AnimatedVisibility(visible = loading) {
                            Loading3Dots(isDark = false)
                        }

                    }

                }


            }
        }
    }


}


@Composable
private fun TextFieldProfileInfo(
    value: String,
    onChangeValue: (it: String) -> Unit,
    icon: ImageVector,
    hint: String,
    isError: Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    textDirection: TextDirection = TextDirection.Rtl
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        value = PastryHelper.pastryByLocate(value),
        textStyle = MaterialTheme.typography.body1.copy(
            textDirection = textDirection
        ),
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = hint,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6

            )
        },
        trailingIcon = {
            Icon(
                icon,
                contentDescription = "",
                tint = Color.Black
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
        singleLine = true
    )

}
