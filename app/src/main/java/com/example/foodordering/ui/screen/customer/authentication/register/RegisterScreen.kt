package com.example.foodordering.ui.screen.customer.authentication.register

import android.content.res.Configuration
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.foodordering.ui.theme.DarkColorScheme
import com.example.foodordering.ui.theme.TextColor

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel = viewModel(),
) {

    val tmp by remember {
        viewModel.registerSuccess
    }

    when (tmp) {
        true -> onRegisterSuccess()
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
                state = viewModel.username,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Username",
                icon = Icons.Default.Person
            )


            MyTextField(
                state = viewModel.email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Email",
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

            MyTextField(
                state = viewModel.phoneNumber,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                labelString = "Phone Number",
                icon = Icons.Default.Phone
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
                    onRegisterSuccess()
                }) {
                    Text(
                        text = "Have An Account ?",
                        color = TextColor,
                    )
                }
            }

        }

    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun LoginScreenDarkPreview() {
    RegisterScreen({})

}