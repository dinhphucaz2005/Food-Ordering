package com.example.foodordering.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodordering.ui.theme.DarkColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    state: MutableState<String>, modifier: Modifier, labelString: String, icon: ImageVector
) {

    TextField(
        singleLine = true,
        value = state.value,
        leadingIcon = {
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Icon(
                        imageVector = icon,
                        tint = DarkColorScheme.onPrimary, contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                }
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = labelString,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        shape = RoundedCornerShape(24.dp),
        onValueChange = {
            state.value = it
        }
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun MyTextFieldPreview() {
    MyTextField(
        state = mutableStateOf(""),
        modifier = Modifier.fillMaxWidth(),
        labelString = "Username",
        icon = Icons.Default.Email
    )
}


