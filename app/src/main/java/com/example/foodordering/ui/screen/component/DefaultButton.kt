package com.example.foodordering.ui.screen.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodordering.ui.theme.Tertiary

@Preview
@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    color: Color = Tertiary,
    content: @Composable RowScope.() -> Unit = {}
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        content()
    }
}