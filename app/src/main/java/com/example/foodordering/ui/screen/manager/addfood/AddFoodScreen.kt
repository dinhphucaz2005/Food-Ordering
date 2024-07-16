package com.example.foodordering.ui.screen.manager.addfood

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodordering.ui.screen.component.DefaultButton
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.Tertiary

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
                onClick = {
                    onCancel()
                },
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
            DefaultButton(onClick = {
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
            DefaultButton(onClick = {
                viewModel.addToFireBase()
            }, modifier = Modifier.width(100.dp)) {
                Text(text = "Add")
            }
            DefaultButton(onClick = {
                onCancel()
            }, color = Color(0xFFe04048), modifier = Modifier.width(100.dp)) {
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