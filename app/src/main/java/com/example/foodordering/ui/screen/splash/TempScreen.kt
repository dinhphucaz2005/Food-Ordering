package com.example.foodordering.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodordering.R
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.DarkColorScheme

@Composable
fun Item(
    modifier: Modifier, painter: Painter, color: Color, text: String
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(color = Background),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Text(
            text = text,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun TempScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = "Restaurant Staff Manager", fontSize = 28.sp, fontWeight = FontWeight.Bold
        )

        Text(
            text = "Select Your Role to Login",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp)
        )

        Row(
            modifier = Modifier.height(250.dp)
        ) {
            Item(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                painterResource(id = R.drawable.main),
                color = DarkColorScheme.primary,
                text = "Manager"
            )
            Item(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                painterResource(id = R.drawable.main2),
                color = DarkColorScheme.primary,
                text = "Chef"
            )
        }

        Row(
            modifier = Modifier.height(250.dp)
        ) {
            Item(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                painterResource(id = R.drawable.main3),
                color = DarkColorScheme.primary,
                text = "Cashier"
            )
            Item(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                painterResource(id = R.drawable.main4),
                color = DarkColorScheme.primary,
                text = "Customer"
            )
        }

    }
}


val myBrush = Brush.linearGradient(
    listOf(
        Color(0xFFffa3ff), Color(0xFF30255a)
    )
)

@Composable
fun DrawerItem(
    modifier: Modifier, isDividerVisible: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isDividerVisible) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp), color = Color.LightGray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.VerifiedUser, contentDescription = null)
            Text(
                text = "Hello",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun DrawerDetail() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
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
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.burger),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
        }

        LazyColumn {
            items(10) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    DrawerItem(Modifier, index != 0)
                }
            }
        }
    }
}
