package com.example.foodordering.ui.screen.manager.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Food
import com.example.foodordering.ui.theme.Background


@Preview
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel()
) {

    val listFood = viewModel.listFoodState

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {

        itemsIndexed(listFood) { index, item ->
            FoodItem(item, index) {}
        }
    }

}


@Composable
fun FoodItem(
    food: Food = FakeData.provideFoods()[0],
    indexed: Int = 0, onclick: () -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(10.dp)
        .clickable { onclick() }) {
        val image = createRef()
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xFF3D3D3C),
                            Color(0xFF2B2A29),
                            Color(0xFF3D3D3C),
                            Color(0xFF2B2A29),
                            Color(0xFF3D3D3C),
                            Color(0xFF2B2A29),
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    ), shape = RoundedCornerShape(10.dp)
                )
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))
                .aspectRatio(1f)
                .constrainAs(image) {
                    end.linkTo(parent.end)
                }, contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(food.gallery.first()),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .constrainAs(
                    createRef()
                ) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = food.name,
                modifier = Modifier.padding(bottom = 20.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "$ ${food.price}",
            )
        }
    }
}

