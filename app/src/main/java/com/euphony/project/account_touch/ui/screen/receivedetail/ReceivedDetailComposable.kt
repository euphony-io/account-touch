package com.euphony.project.account_touch.ui.screen.receivedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.euphony.EuphonyViewModel
import com.euphony.project.account_touch.ui.screen.main.BankImage
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.Gray_F4F4F4
import com.euphony.project.account_touch.ui.viewmodel.ReceivedViewModel
import com.euphony.project.account_touch.utils.AssetsUtil

@Composable
fun ReceivedDetailScreen(
    id: Long,
    receivedViewModel: ReceivedViewModel,
    onBackClick: () -> Unit,
) {
    val received by receivedViewModel.getReceived(id).observeAsState()
    if (received != null) ReceivedDetail(received!!, onBackClick)
}

@Composable
fun ReceivedDetail(received: Received, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            modifier = Modifier.padding(start = 12.dp),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "뒤로가기",
                            tint = Blue_6D95FF,
                        )
                    }
                },
                elevation = 0.dp
            )
        },
        content = {
            Column {
                SpeakerInfo(received)
                ReceivedAccount(received)
            }
        }
    )
}

@Composable
fun SpeakerInfo(received: Received) {
    Text(
        modifier = Modifier
            .padding(vertical = 36.dp, horizontal = 16.dp),
        text = "${received.createDate}에 받은 계좌",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Blue_6D95FF
    )
}

@Composable
fun ReceivedAccount(received: Received) {
    val imageBitmap = AssetsUtil.getBitmap(LocalContext.current, "banks/bnk_bank")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Gray_F4F4F4,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(vertical = 24.dp)) {
                BankImage(imageBitmap)
            }
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                text = received.accountNumber,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 30.sp
            )
        }
    }
}
