package com.example.foodordering.ui.screen.customer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Food
import com.example.foodordering.ui.theme.Tertiary
import com.example.foodordering.ui.theme.TextColor

@Preview
@Composable
fun FoodRecyclerView(
    listFood: List<Food> = FakeData.provideListFood(),
    addCart: (Food) -> Unit = {}
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(listFood) { index, item ->
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .border(
                        width = 2.dp, color = Tertiary, shape = RoundedCornerShape(24.dp)
                    )
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = rememberAsyncImagePainter(item.gallery.first()),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Text(
                    text = item.id,
                    fontWeight = FontWeight.Normal,
                    color = TextColor
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$${item.price}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Tertiary
                    )

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Tertiary)
                            .padding(4.dp)
                            .clickable {
                                addCart(item)
                            }, contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp, 20.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White
                        )
                    }

                }
            }
        }
    }

}
