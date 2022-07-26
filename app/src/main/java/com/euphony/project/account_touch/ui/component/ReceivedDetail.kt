package com.euphony.project.account_touch.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF

@Composable
fun ReceivedDetailScreen() { // TODO: viewMode, onBackClick as parameters
    ReceivedDetail()
}

@Composable
fun ReceivedDetail() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                backgroundColor = Color.White,
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 12.dp),
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "뒤로가기",
                        tint = Blue_6D95FF
                    )
                },
                elevation = 0.dp
            )
        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun ReceivedDetailPreview() {
    ReceivedDetail()
}
