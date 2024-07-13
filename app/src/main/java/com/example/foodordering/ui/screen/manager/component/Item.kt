package com.example.foodordering.ui.screen.manager.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodordering.R
import com.example.foodordering.domain.model.Invoice
import com.example.foodordering.ui.theme.Tertiary
import com.example.foodordering.util.FakeData


@Composable
fun Item(modifier: Modifier = Modifier, invoice: Invoice = FakeData.provideInvoices()[0]) {
    ConstraintLayout(
        modifier = modifier
            .background(
                color = Tertiary,
                shape = RoundedCornerShape(20.dp)
            )
            .fillMaxWidth()
            .height(90.dp)
            .padding(10.dp)
    ) {

        val img = createRef()

        Image(
            painter = painterResource(id = R.drawable.burger), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .constrainAs(img) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        val idInvoice = createRef()

        Text(
            text = invoice.id,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(idInvoice) {
                start.linkTo(img.end, margin = 10.dp)
                top.linkTo(parent.top, margin = 10.dp)
            }
        )

        val statusInvoice = createRef()

        Text(
            text = invoice.status,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(statusInvoice) {
                start.linkTo(idInvoice.start)
                top.linkTo(idInvoice.bottom)
            },
            color = Color.Red
        )

        val itemSetting = createRef()

        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null,
            modifier = Modifier.constrainAs(itemSetting) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })


        val totalPrice = createRef()
        Text(
            text = invoice.totalPrice.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(totalPrice) {
                top.linkTo(idInvoice.top)
                end.linkTo(itemSetting.start)
            })

        val tableNumber = createRef()

        Text(
            text = invoice.tableNumber.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(tableNumber) {
                end.linkTo(totalPrice.end)
                top.linkTo(totalPrice.bottom)
            })


    }
}

@Preview
@Composable
fun ItemPreview() {
    Item()
}

@Composable
fun InvoiceRecyclerView(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.White
            ),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(FakeData.provideInvoices().size) {
            Item(invoice = FakeData.provideInvoices()[it])
        }
    }
}

@Preview
@Composable
fun InvoiceRecyclerViewPreview() {
    InvoiceRecyclerView()
}