package com.example.foodordering.ui.screen.customer.main.cart

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.foodordering.R
import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.FoodDTO
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.extension.toVND
import com.example.foodordering.ui.navigation.Routes
import com.example.foodordering.ui.screen.splash.WaitingScreen
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.Tertiary
import com.example.foodordering.ui.theme.TextColor
import com.example.foodordering.util.AppResource

@Preview
@Composable
fun Preview() {
    CartScreen(
        NavHostController(LocalContext.current), CartViewModel(object : CustomerRepository {
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
fun CartScreen(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val cart by viewModel.cart.collectAsState()
    val isUpdatedCart by viewModel.isUpdatedCart.collectAsState()
    val inProgress by viewModel.inProgress.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Background
            )
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Background
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    modifier = Modifier.size(32.dp, 32.dp),
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = TextColor,
                )
            }

            Text(
                text = "Cart Items", color = TextColor,
                modifier = Modifier
                    .padding(end = 32.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                cart?.let {
                    itemsIndexed(it.foods) { _, food ->
                        ConstraintLayout(
                            Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .background(
                                    Color.White
                                )
                        ) {
                            val imageRef = createRef()

                            Image(modifier = Modifier
                                .constrainAs(imageRef) {
                                    start.linkTo(parent.start)
                                }
                                .fillMaxHeight()
                                .aspectRatio(1f),
                                painter = rememberAsyncImagePainter(model = food.gallery[0]),
                                contentDescription = "",
                                contentScale = ContentScale.Crop)

                            val rowRef = createRef()

                            Row(modifier = Modifier
                                .constrainAs(rowRef) {
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                }
                                .wrapContentHeight()
                                .wrapContentWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Background)
                                .padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Tertiary)
                                        .size(32.dp, 32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(onClick = {
                                        viewModel.removeCart(food.id)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_minimize_24),
                                            contentDescription = "",
                                            modifier = Modifier.padding(bottom = 10.dp),
                                            tint = Color.White
                                        )
                                    }
                                }

                                Text(
                                    text = "${food.quantity}",
                                    color = Tertiary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Tertiary)
                                        .size(32.dp, 32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(onClick = {
                                        viewModel.addCart(food.id)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "",
                                            tint = Color.White,
                                            modifier = Modifier.size(20.dp, 20.dp)
                                        )
                                    }
                                }
                            }

                            val priceRef = createRef()

                            Text(text = "$ ${food.price.toVND()}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextColor,
                                modifier = Modifier.constrainAs(priceRef) {
                                    top.linkTo(rowRef.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(imageRef.end, margin = 8.dp)
                                })

                            val foodRef = createRef()

                            Text(text = food.name,
                                fontSize = 16.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.constrainAs(foodRef) {
                                    top.linkTo(parent.top, margin = 8.dp)
                                    start.linkTo(priceRef.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                })

                        }
                    }
                }
            }

            ApplyCoupon()

            Text(
                modifier = Modifier,
                text = "Price Details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(thickness = 2.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Total Amount Payable",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = cart?.totalPrice.toVND(),
                    fontSize = 16.sp,
                    color = Tertiary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }

            Button(
                onClick = { viewModel.updateCart()},
                colors = ButtonDefaults.buttonColors(containerColor = Tertiary),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 12.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Checkout", color = Color.White, fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(20.dp, 20.dp)
                )
            }
        }
    }


    if (inProgress) {
        WaitingScreen()
    }
    if (isUpdatedCart)
        navController.navigate(Routes.MAIN_CONFIRM)

}

@Composable
fun ApplyCoupon(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            text = "Apply Coupon", fontSize = 16.sp, fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {

            var userApplyCode by remember { mutableStateOf("") }

            TextField(
                value = userApplyCode,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Background,
                    unfocusedContainerColor = Background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = TextColor,
                    unfocusedTextColor = TextColor,
                    focusedLabelColor = TextColor,
                    unfocusedLabelColor = TextColor
                ),
                modifier = Modifier.fillMaxWidth(0.6f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = "Enter Code", fontWeight = FontWeight.Bold) },
                shape = RoundedCornerShape(8.dp),
                onValueChange = {
                    userApplyCode = it
                },
            )

            Button(
                onClick = {},
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Tertiary
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .height(56.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Apply", color = Color.White
                )
            }
        }
    }
}