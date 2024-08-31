package com.example.foodordering.ui.screen.customer.authentication.register


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodordering.R
import com.example.foodordering.di.FakeModule
import com.example.foodordering.ui.component.MyTextField
import com.example.foodordering.ui.screen.splash.WaitingScreen
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.FoodOrderingTheme
import com.example.foodordering.ui.theme.TextColor

@Preview
@Composable
fun Preview() {
    FoodOrderingTheme {
        RegisterScreen(
            FakeModule.provideNavController(),
            FakeModule.provideRegisterViewModel()
        )
    }
}

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
    ) {

        val (image, column) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(column) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 50.dp
                ),
            verticalArrangement = Arrangement.Center
        ) {
            MyTextField(
                state = viewModel.email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Email",
                icon = Icons.Default.Email
            )

            MyTextField(
                state = viewModel.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Name",
                icon = Icons.Default.Person
            )

            MyTextField(
                state = viewModel.password,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Password",
                icon = Icons.Default.Lock
            )

            MyTextField(
                state = viewModel.phoneNumber,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Phone Number",
                icon = Icons.Default.Phone
            )

            MyTextField(
                state = viewModel.address,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Address",
                icon = Icons.Default.LocationOn
            )

            Button(
                onClick = {
                    viewModel.register()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Register \uD83D\uDE0B",
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = {
                    navController.popBackStack()
                }) {
                    Text(
                        text = "Have An Account ?",
                        color = TextColor,
                    )
                }
            }

        }
    }

    viewModel.registerSuccess.value.let {
        if (it) {
            navController.popBackStack()
        }
    }

    if (viewModel.isLoading.value) {
        WaitingScreen()
    }
}