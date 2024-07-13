package com.example.foodordering.ui.screen.customer.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodordering.R
import com.example.foodordering.ui.component.MyTextField
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.Tertiary
import com.example.foodordering.ui.theme.TextColor

@Preview
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    navigateRegister: () -> Unit = {},
    viewModel: LoginViewModel = viewModel(),
) {

    when (viewModel.loginSuccess.value) {
        true -> onLoginSuccess()
        else -> {
            //TODO("show error message")
        }
    }

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

            MyTextField(
                state = viewModel.username,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Username/Email",
                icon = Icons.Default.Email
            )

            MyTextField(
                state = viewModel.password,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Password",
                icon = Icons.Default.Lock
            )

            Button(
                onClick = {
                    viewModel.login()
                },
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
                onClick = {
                    navigateRegister()
                },
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

//        if (viewModel.isLoginLoading.value) {
//            Text(text = "Login",
//                modifier = Modifier.constrainAs(createRef()) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//            )
////            LoadingScreen(Modifier.constrainAs(createRef()) {
////                top.linkTo(parent.top)
////                start.linkTo(parent.start)
////            })
//        } else {
//            Text(text = "Login **************",
//                modifier = Modifier.constrainAs(createRef()) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//            )
//        }

    }
}

@Preview
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}
