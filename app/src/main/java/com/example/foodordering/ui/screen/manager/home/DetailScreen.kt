package com.example.foodordering.ui.screen.manager.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.foodordering.domain.model.Food
import com.example.foodordering.ui.theme.DarkColorScheme
import com.example.foodordering.util.FakeData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Add food") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                },
                containerColor = DarkColorScheme.primary,
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        innerPadding
                    )
            ) {
                items(10) {
                    FoodItem()
                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Add Food")
                    }
                }
            }


        }
    )

}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}

@Preview
@Composable
fun FoodItemPreview() {
    FoodItem()
}

@Composable
fun FoodItem(
    food: Food = FakeData.provideFoods()[0],
    indexed: Int = 0, onclick: () -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier
        .background(Color.White)
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
                text = food.name, style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 20.dp),
                fontSize = 22.sp
            )
            Text(
                fontSize = 18.sp,
                text = food.price.toString(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}