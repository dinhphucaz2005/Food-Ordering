package com.example.foodordering.ui.screen.manager.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodordering.R
import com.example.foodordering.ui.screen.manager.component.BillRecyclerView
import com.example.foodordering.ui.theme.Tertiary


@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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
                tint = Tertiary
            )

            Text(
                text = "All Order",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                color = Tertiary
            )
            Icon(
                imageVector = Icons.Outlined.Search, contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                tint = Tertiary
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(
                        color = Tertiary,
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
        val bills = viewModel.billList
        BillRecyclerView(bills = bills)
    }
}

@Composable
fun ManagerDrawer(
    onClick: List<() -> Unit> = listOf()
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.8f)
            .background(Tertiary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xFFffbd71),
                            Color(0xFFffa5fe)
                        )
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.burger),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
        }

        val listString =
            listOf("Home", "Foods", "Statistic", "Settings", "Help")
        val listIcon =
            listOf(
                Icons.Default.Home,
                Icons.Default.Fastfood,
                Icons.Default.BarChart,
                Icons.Default.Settings,
                Icons.AutoMirrored.Default.Help
            )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, start = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(listString.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clickable {
                            onClick[index]()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = listIcon[index],
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                    Text(
                        text = listString[index], modifier = Modifier.padding(start = 12.dp),
                        fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(
                onClick = { }, shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = null,
                    tint = Tertiary
                )
                Text(text = "Logout", modifier = Modifier.padding(start = 12.dp), color = Tertiary)
            }
        }

    }
}