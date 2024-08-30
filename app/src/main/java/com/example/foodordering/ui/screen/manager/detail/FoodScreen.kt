package com.example.foodordering.ui.screen.manager.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Food
import com.example.foodordering.ui.screen.component.DefaultButton
import com.example.foodordering.ui.theme.Background


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: FoodViewModel = hiltViewModel()
) {

    val listFood = viewModel.listFoodState

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        itemsIndexed(listFood) { _, item ->
            FoodItem(food = item,
                onRemoveClick = { foodId ->
                    viewModel.removeFood(foodId)
                },
                onEditClick = { foodId ->
                    TODO("Navigation to edit food")
                })
        }
    }

}


@Preview
@Composable
fun FoodItem(
    food: Food = FakeData.provideFoods()[0],
    onRemoveClick: (String) -> Unit = {}, // FoodId
    onEditClick: (String) -> Unit = {}, // FoodId
) {
    var showButtons by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            showButtons = true
                        }
                    )
                }
        ) {
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
        if (showButtons) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DefaultButton(onClick = {
                    onEditClick(food.id)
                }, modifier = Modifier.width(100.dp)) {
                    Text(text = "Edit")
                }
                DefaultButton(onClick = {
                    onRemoveClick(food.id)
                }, color = Color(0xFFe04048), modifier = Modifier.width(100.dp)) {
                    Text(text = "Remove")
                }
            }
        }
    }
}

