package com.example.foodordering.ui.screen.manager.detail

import android.widget.TextView
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Food
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.DarkColorScheme
import com.example.foodordering.ui.theme.Tertiary
import com.example.foodordering.ui.theme.YeonSungFont
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = viewModel()
) {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val listFood = viewModel.listFoodState

    Scaffold(
        modifier = Modifier
            .background(Background)
            .fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Add food") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                },
                shape = RoundedCornerShape(8.dp),
                containerColor = Tertiary,
            )
        },
        topBar = {
            Text(
                text = "List Food",
                fontFamily = YeonSungFont,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = Tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        },
        content = { innerPadding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(
                        innerPadding
                    )
            ) {

                itemsIndexed(listFood) { index, item ->
                    FoodItem(item, index) {}
                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {

                    AddFood(onCancel = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    })
                }
            }


        }
    )

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

@Preview
@Composable
fun AddFood(
    onCancel: () -> Unit = {},
    viewModel: AddFoodViewModel = viewModel()
) {

    val imageList = viewModel.imageListState
    val id = viewModel.idState
    val name = viewModel.nameState
    val price = viewModel.priceState

    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {

            IconButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            ) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
            }

            Text(
                text = "Add Item", modifier = Modifier
                    .weight(1f)
                    .padding(end = 48.dp),
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
        }

        CustomTextView(
            text = id,
            modifier = Modifier
                .fillMaxWidth(), label = "Id"
        )

        CustomTextView(
            text = name,
            modifier = Modifier
                .fillMaxWidth(), label = "Name"
        )

        CustomTextView(
            text = price,
            modifier = Modifier
                .fillMaxWidth(), label = "Price"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Gallery", modifier = Modifier.weight(1f), fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = {
                viewModel.addImage()
            }) {
                Text(text = "Add Image")
            }
        }

        if (imageList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = "No Image Found")
            }
        } else {

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                item {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "${imageList.size} Images Found")
                    }
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                viewModel.addToFireBase()
            }, modifier = Modifier.width(100.dp)) {
                Text(text = "Add")
            }
            Button(onClick = {
                onCancel()
            }, modifier = Modifier.width(100.dp)) {
                Text(text = "Cancel")
            }
        }

    }
}

@Composable
fun CustomTextView(
    modifier: Modifier = Modifier,
    text: MutableState<String> = mutableStateOf(""),
    label: String = "Item Name",
) {
    TextField(
        singleLine = true,
        value = text.value,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Tertiary,
            unfocusedContainerColor = Tertiary,
            unfocusedTextColor = Tertiary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        label = {
            Text(
                text = label,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        shape = RoundedCornerShape(8.dp),
        onValueChange = {
            text.value = it
        },
        modifier = modifier
    )
}