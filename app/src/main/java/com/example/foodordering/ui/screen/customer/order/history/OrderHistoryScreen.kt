package com.example.foodordering.ui.screen.customer.order.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.foodordering.di.FakeData
import com.example.foodordering.di.FakeModule
import com.example.foodordering.domain.model.Invoice
import com.example.foodordering.extension.toVND
import com.example.foodordering.ui.navigation.Routes
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.FoodOrderingTheme
import com.example.foodordering.ui.theme.TextColor


@Preview
@Composable
fun Preview() {
    FoodOrderingTheme {
        OrderHistoryScreen(
            FakeModule.provideNavController(),
            FakeModule.provideOrderHistoryViewModel()
        )
    }
}

@Composable
fun OrderHistoryScreen(
    navController: NavController,
    viewModel: OrderHistoryViewModel
) {

    val invoices by viewModel.invoices.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        val (text, backButton, content) = createRefs()
        Text(
            text = "Order History",
            style = TextStyle(color = TextColor, fontWeight = FontWeight.Bold, fontSize = 32.sp),
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(backButton) {
                start.linkTo(parent.start)
                bottom.linkTo(text.bottom)
                top.linkTo(text.top)
            }
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
        }

        LazyColumn(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(text.bottom, margin = 12.dp)
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val invoiceModifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(8.dp)

            itemsIndexed(invoices) { _, invoice ->
                Item(invoiceModifier.clickable {
                    navController.navigate(Routes.ORDERHISTORY_DETAIL + "/" + invoice.id)
                }, invoice)
            }
        }
    }
}

@Preview
@Composable
fun Item(modifier: Modifier = Modifier, invoice: Invoice = FakeData.provideInvoice()) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = "ID: ${invoice.id}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total: ${invoice.total.toVND()}",
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Date: ${invoice.dateCreated}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
