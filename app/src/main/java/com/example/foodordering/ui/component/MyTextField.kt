@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.foodordering.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodordering.ui.theme.DarkColorScheme


@Preview
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    state: MutableState<String> = mutableStateOf(""),
    labelString: String = "Username",
    icon: ImageVector = Icons.Default.Email
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
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier.fillMaxWidth(),
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


@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    icon: ImageVector? = null,
) {
    TextField(
        singleLine = true,
        value = value,
        leadingIcon = {
            icon?.let {
                Icon(
                    imageVector = icon,
                    tint = DarkColorScheme.onPrimary, contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier,
        label = {
            Text(
                text = label,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        shape = RoundedCornerShape(24.dp),
        onValueChange = { onValueChange(it) }
    )
}


