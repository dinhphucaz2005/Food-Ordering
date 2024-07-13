package com.example.foodordering.ui.screen.customer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodordering.R
import com.example.foodordering.ui.theme.Primary
import com.example.foodordering.ui.theme.Tertiary

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    itemList: List<String> = listOf(
        "All",
        "Burgers", "Pizza", "Healthy",
        "Burgers", "Pizza", "Healthy",
        "Burgers", "Pizza", "Healthy",
    ),
    categoryImagesList: List<Int> = listOf(
        R.drawable.burger2,
        R.drawable.burger2,
        R.drawable.pizza,
        R.drawable.salad,
        R.drawable.burger2,
        R.drawable.pizza,
        R.drawable.salad,
        R.drawable.burger2,
        R.drawable.pizza,
        R.drawable.salad,
    )
) {
    LazyRow(
        modifier = modifier.height(50.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(itemList.size) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        color = if (item == 0) Tertiary
                        else Primary
                    )
                    .fillMaxHeight()
                    .padding(top = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(categoryImagesList[item]),
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(start = 20.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier.padding(
                        start = 5.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
                    ),
                    text = itemList[item],
                    color = if (item == 0) Color.White else Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun CategoriesPreview() {
    Categories(Modifier.fillMaxWidth())
}