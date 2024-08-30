package com.example.foodordering.ui.screen.customer.main.confirm

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.FoodDTO
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.extension.toVND
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.Tertiary
import com.example.foodordering.ui.theme.TextColor
import com.example.foodordering.util.AppResource

@Preview
@Composable
fun Preview() {
    ConfirmScreen(
        NavHostController(LocalContext.current),
        ConfirmViewModel(object : CustomerRepository {
            override suspend fun getFoods(): AppResource<List<FoodDTO>> {
                TODO("Not yet implemented")
            }

            override suspend fun addCart(productId: String): AppResource<CartDTO> {
                TODO("Not yet implemented")
            }

            override suspend fun getFoodById(id: String): FoodDTO {
                TODO("Not yet implemented")
            }

            override suspend fun getCart(): AppResource<CartDTO> {
                TODO("Not yet implemented")
            }

            override suspend fun updateCart(
                foodId: String,
                cartId: String,
                quantity: Int
            ): AppResource<*> {
                TODO("Not yet implemented")
            }

        })
    )
}


@Composable
fun ConfirmScreen(
    navController: NavHostController,
    viewModel: ConfirmViewModel = hiltViewModel(),
) {

    val cart by viewModel.cart.collectAsState()
//    val cart = FakeData.provideCart()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(12.dp)
    ) {
        val (backBtn, text, lazyColumn, confirmButton) = createRefs()

        IconButton(onClick = {
            TODO("Navigate MAIN_HOME screen")
        }, modifier = Modifier.constrainAs(backBtn) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }) {
            Icon(
                modifier = Modifier.size(32.dp, 32.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "",
                tint = TextColor,
            )
        }
        Text(
            text = "Confirm", color = TextColor,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(backBtn.bottom)
                },
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        )

        LazyColumn(
            modifier = Modifier
                .constrainAs(lazyColumn) {
                    top.linkTo(backBtn.bottom)
                    bottom.linkTo(confirmButton.top)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFffffff))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            cart?.let {
                itemsIndexed(it.foods) { _, food ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        val imageModifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF35536b))
                        AsyncImage(
                            model = food.gallery.first(),
                            contentDescription = null,
                            modifier = imageModifier,
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            Text(
                                text = food.name,
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextColor
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Giá: ${food.price.toVND()}    Số lượng: ${food.quantity}",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextColor
                                )
                            )
                            Text(
                                text = "Tổng: ${food.price * food.quantity}",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextColor
                                )
                            )
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                viewModel.confirm()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Tertiary),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 12.dp)
                .fillMaxWidth()
                .height(60.dp)
                .constrainAs(confirmButton) {
                    bottom.linkTo(parent.bottom)
                },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Confirm", color = Color.White, fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )
        }
    }
}


