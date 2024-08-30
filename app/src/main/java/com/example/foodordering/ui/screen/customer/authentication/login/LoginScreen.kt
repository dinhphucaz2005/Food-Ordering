package com.example.foodordering.ui.screen.customer.authentication.login

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodordering.R
import com.example.foodordering.ui.component.MyTextField
import com.example.foodordering.ui.screen.splash.WaitingScreen
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.Tertiary
import com.example.foodordering.ui.theme.TextColor
import com.example.foodordering.ui.navigation.Routes

@Preview
@Composable
fun LoginScreen(
    navController: NavHostController = NavHostController(LocalContext.current),
    viewModel: LoginViewModel = hiltViewModel(),
) {
    var email by remember { mutableStateOf("dinh.phuc.17.5.25@gmail.com") }
    var password by remember { mutableStateOf("00000000") }

    val isLoginSuccess by viewModel.isLoginSuccess.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoginSuccess) {
        navController.navigate(Routes.MAIN)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
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
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 50.dp
                )
                .constrainAs(column) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            verticalArrangement = Arrangement.Center
        ) {

            val textFieldModifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)

            MyTextField(
                modifier = textFieldModifier,
                value = email,
                onValueChange = { email = it },
                label = "Email",
                icon = Icons.Default.Email
            )

            MyTextField(
                modifier = textFieldModifier,
                value = password,
                onValueChange = { password = it },
                label = "Password",
                icon = Icons.Default.Lock
            )

            Button(
                onClick = { viewModel.login(email, password) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Login to account \uD83D\uDE0B",
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate(Routes.AUTH_REGISTER) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Tertiary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Create new account",
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }

            val errorState by remember {
                viewModel.errorMessage
            }
            if (errorState.isNotEmpty()) {
                Text(
                    text = errorState,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (errorState.isEmpty()) 8.dp else 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = { }) {
                    Text(
                        text = "Forgot Password ?",
                        color = TextColor,
                    )
                }
            }
        }

        if (isLoading) {
            WaitingScreen()
        }
    }
}