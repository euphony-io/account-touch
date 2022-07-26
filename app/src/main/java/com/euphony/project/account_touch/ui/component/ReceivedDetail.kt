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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.data.entity.Received
import com.euphony.project.account_touch.data.entity.model.UserIcon
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF

@Composable
fun ReceivedDetailScreen() { // TODO: viewMode, onBackClick as parameters
    val received = Received(
        accountNickname = "붕어빵",
        accountNumber = "123456789",
        speakerNickName = "붕어빵 사장",
        speakerIcon = UserIcon.GHOST,
    )
    ReceivedDetail(received)
}

@Composable
fun ReceivedDetail(received: Received) {
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
        },
        content = {
            SpeakerInfo(received)
        }
    )
}

@Composable
fun SpeakerInfo(received: Received) {
    Text(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp),
        text = "${received.speakerNickName}님꼐서 보내신\n${received.accountNickname} 계좌입니다.",
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = Blue_6D95FF
    )
}

@Preview(showBackground = true)
@Composable
fun ReceivedDetailPreview() {
    val received = Received(
        accountNickname = "붕어빵",
        accountNumber = "123456789",
        speakerNickName = "붕어빵 사장",
        speakerIcon = UserIcon.GHOST,
    )
    ReceivedDetail(received)
}
