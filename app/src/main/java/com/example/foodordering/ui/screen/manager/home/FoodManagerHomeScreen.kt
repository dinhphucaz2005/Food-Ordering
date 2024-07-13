package com.example.foodordering.ui.screen.manager.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodordering.ui.component.TopAppBarHome
import com.example.foodordering.ui.screen.manager.component.InvoiceRecyclerView
import com.example.foodordering.ui.theme.DarkColorScheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodManagerHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = DarkColorScheme.primary
                )
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            TopAppBarHome("Food Manager")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.CalendarMonth, contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                tint = DarkColorScheme.onPrimary
            )

            Text(
                text = "All Order", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.Search, contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                tint = DarkColorScheme.onPrimary
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(
                        color = Color(0xFFfea0a0),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add, contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    tint = Color.White
                )
            }
        }
        InvoiceRecyclerView()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    FoodManagerHomeScreen()
}