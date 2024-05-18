package ir.hoseinahmadi.frenchpastry.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalPhone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import ir.hoseinahmadi.frenchpastry.ui.theme.DarkCyan
import ir.hoseinahmadi.frenchpastry.ui.theme.body1
import ir.hoseinahmadi.frenchpastry.ui.theme.body2
import ir.hoseinahmadi.frenchpastry.ui.theme.h5
import ir.hoseinahmadi.frenchpastry.ui.theme.h6

@Composable
fun MyEditText(
    value: String,
    placeholder: String,
    onValueChange: (it: String) -> Unit,
    timer: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
    onError: Boolean = false,
) {

    TextField(
        modifier = Modifier
            .width(250.dp)
            .height(47.dp)
            .border(0.9.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp)),
        value = value,
        isError = onError,
        singleLine = true,
        trailingIcon = {
            if (timer.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray.copy(0.25f))
                        .size(50.dp, 32.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = timer,
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                }

            }
            else {
                Icon(Icons.Filled.LocalPhone,
                    contentDescription = "",
                    tint = Color.Black)
            }
        },
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = MaterialTheme.colorScheme.DarkCyan,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            errorTextColor = Color.Red
        ),
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.h6,
                color = Color.DarkGray
            )

        },
        textStyle = MaterialTheme.typography.body2.copy(
            textDirection = TextDirection.Ltr,
        ),

        )

}