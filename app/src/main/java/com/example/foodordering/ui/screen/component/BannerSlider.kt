package com.example.foodordering.ui.screen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun BannerSlider(
    urls: List<String> = listOf(
        "https://th.bing.com/th/id/OIP.Y5coy57_PPEyuXaA-fyhDQHaFj?rs=1&pid=ImgDetMain",
        "https://img.freepik.com/premium-psd/special-delicious-burger-sale-social-media-post-web-banner-template-design_565857-172.jpg?size=626&ext=jpg",
        "https://th.bing.com/th/id/OIP.BnU7_w0RgvR2hQYXjxAHLgHaHa?w=826&h=826&rs=1&pid=ImgDetMain"
    ),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
    border: Int = 16
) {
    val pagerState = rememberPagerState(pageCount = {
        urls.size
    })

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = border.dp),
        pageSpacing = 8.dp,
        verticalAlignment = Alignment.Top,
    ) { page ->
        AsyncImage(
            model = urls[page],
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
