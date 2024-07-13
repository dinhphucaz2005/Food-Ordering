package com.example.foodordering.ui.screen.customer.home


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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodordering.R
import com.example.foodordering.ui.component.TopAppBarHome
import com.example.foodordering.ui.screen.customer.cart.CartViewModel
import com.example.foodordering.ui.screen.customer.component.Categories
import com.example.foodordering.ui.screen.customer.component.FoodRecyclerView
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.DarkColorScheme
import com.example.foodordering.ui.theme.Tertiary
import com.example.foodordering.ui.theme.TextColor
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCheckout: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CustomerDrawerDetail()
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
        ) {
            val (topBarRef, content) = createRefs()
            Column(
                modifier = Modifier
                    .background(
                        color = DarkColorScheme.primary
                    )
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(180.dp)
                    .constrainAs(topBarRef) {
                        top.linkTo(parent.top)
                    }
            ) {
                TopAppBarHome(dashboardOnClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                })

                var searchValue by remember {
                    mutableStateOf("")
                }

                SearchBar(
                    modifier = Modifier.padding(top = 20.dp),
                    placeholder = { Text(text = "Search ...") },
                    query = searchValue,
                    onQueryChange = { searchValue = it },
                    onSearch = { },
                    active = false,
                    onActiveChange = {},
                    colors = SearchBarDefaults.colors(
                        containerColor = Background,
                        inputFieldColors = TextFieldDefaults.colors(
                            focusedTextColor = TextColor,
                            unfocusedTextColor = TextColor,
                            cursorColor = TextColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                ) {

                }

            }

            val checkoutBarRef = createRef()

            Row(
                modifier = Modifier
                    .constrainAs(checkoutBarRef) {
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart, contentDescription = null,
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp, start = 12.dp)
                        .fillMaxHeight()
                        .aspectRatio(1f),
                    tint = Tertiary
                )
                val cart = cartViewModel.cart

                Text(
                    text = "$ ${cart.sumOf { it.food.price * it.quantity }}",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp),
                    textAlign = TextAlign.End,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Tertiary
                )
                Button(
                    onClick = {
                        onCheckout()
                    }, modifier = Modifier.fillMaxHeight(),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Tertiary
                    )
                ) {
                    Text(
                        text = "Checkout", modifier = Modifier,
                        fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .constrainAs(content) {
                        top.linkTo(topBarRef.bottom)
                    }
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Special Offers",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(top = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF3AC8B))
                    ) {
                        val imageRef = createRef()
                        Image(
                            painter = painterResource(id = R.drawable.cake),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1.5f)
                                .constrainAs(imageRef) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                                .clip(
                                    RoundedCornerShape(
                                        topStartPercent = 25,
                                        bottomStartPercent = 25
                                    )
                                ),
                            contentScale = ContentScale.Crop
                        )

                        val (text1Ref, text2Ref) = createRefs()

                        Text(
                            text = "Get Special Offer", color = Color.Black, fontSize = 24.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.constrainAs(text1Ref) {
                                top.linkTo(parent.top, margin = 12.dp)
                                start.linkTo(parent.start, margin = 24.dp)
                            }
                        )


                        Text(
                            text = "Up to 40%", color = Color.Black, fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.constrainAs(text2Ref) {
                                top.linkTo(text1Ref.bottom, margin = 12.dp)
                                start.linkTo(parent.start, margin = 24.dp)
                            }
                        )

                    }
                }

                item {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Categories",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Categories(Modifier.padding(top = 10.dp))
                }

                item {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Offer & Deal",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                val listFood = viewModel.listFoodState

                item {
                    FoodRecyclerView(listFood) { food ->
                        cartViewModel.addToCart(0)
                    }
                }
            }
        }
    }


}

@Composable
fun CustomerDrawerDetail() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.8f)
            .background(Tertiary)
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
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.burger),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
        }

        val listString =
            listOf("My Order", "My Profile", "My Cart", "Payment Methods", "Settings", "Help")
        val listIcon =
            listOf(
                Icons.Default.FormatListNumbered,
                Icons.Default.Person,
                Icons.Default.ShoppingCart,
                Icons.Default.Payment,
                Icons.Default.Settings,
                Icons.AutoMirrored.Default.Help
            )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, start = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(listString.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = listIcon[index],
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                    Text(
                        text = listString[index], modifier = Modifier.padding(start = 12.dp),
                        fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(
                onClick = { }, shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = null,
                    tint = Tertiary
                )
                Text(text = "Logout", modifier = Modifier.padding(start = 12.dp), color = Tertiary)
            }
        }

    }
}