package com.example.foodordering.ui.screen.customer.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.foodordering.R
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.Tertiary

@Preview
@Composable
fun DetailScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {


        val imageRef = createRef()
        Image(painter = painterResource(id = R.drawable.cake),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop)

        val favouriteButtonRef = createRef()
        FloatingActionButton(onClick = { },
            shape = CircleShape,
            modifier = Modifier.constrainAs(favouriteButtonRef) {
                top.linkTo(imageRef.bottom)
                end.linkTo(parent.end, margin = 12.dp)
                bottom.linkTo(imageRef.bottom)
            }) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
        }

        val rowRef = createRef()

        Row(modifier = Modifier
            .constrainAs(rowRef) {
                top.linkTo(favouriteButtonRef.top)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(favouriteButtonRef.start, margin = 24.dp)
                bottom.linkTo(favouriteButtonRef.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            .clip(CircleShape)
            .background(Color.White)
            .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            for (i in 1..5) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f),
                    tint = Tertiary
                )
            }
            Text(
                text = "5", fontWeight = FontWeight.Bold, fontSize = 24.sp
            )
        }

        val content = createRef()

        LazyColumn(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(rowRef.bottom, margin = 12.dp)
                start.linkTo(rowRef.start)
                end.linkTo(parent.end, margin = 24.dp)
                bottom.linkTo(parent.bottom, margin = 12.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Slutty Brownie",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    maxLines = 2
                )
            }

            item {
                Text(
                    text = "$45.00",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    maxLines = 2
                )
            }
            item {
                Text(
                    text = "About",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    maxLines = 2
                )
            }
            item {
                Text(
                    text = "Lorem Ipsum is simply dummy text" + " of the printing and typesetting industry." + " Lorem Ipsum has been the industry's standard dummy" + " text ever since the 1500s, when an unknown printer took a " + "galley of type and scrambled it to make a type specimen book" + ". It has survived not only five centuries, but also the leap" + "sheets containing Lorem Ipsum passages, and more recently with" + "software like Aldus PageMaker including versions of Lorem Ipsum.",
                    modifier = Modifier, fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
            item {
                Text(
                    text = "Ingredients",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
            }
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(10) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cake),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.clip(RoundedCornerShape(12.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}