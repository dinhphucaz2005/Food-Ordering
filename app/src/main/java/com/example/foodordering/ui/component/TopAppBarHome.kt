package com.example.foodordering.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.DarkColorScheme
import com.example.foodordering.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHome(
    text: String = "Food Ordering",
    dashboardOnClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = CircleShape)
                .background(Background),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {
                dashboardOnClick()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Dashboard,
                    contentDescription = "",
                    tint = TextColor
                )

            }
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp),
            text = text,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp,
            color = Color(0xFFffffef)
        )
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = CircleShape)
                .background(Background),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "",
                    tint = TextColor
                )

            }
        }

    }
}

@Composable
@Preview
fun TopAppBarHomeScreenPreview() {
    TopAppBarHome()
}
