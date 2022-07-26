package com.euphony.project.account_touch.ui.screen.main.receivedaccounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.ui.screen.common.UserIconItem
import com.euphony.project.account_touch.ui.screen.main.BankImage
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.Gray_9C9C9C
import com.euphony.project.account_touch.ui.theme.Gray_F4F4F4
import com.euphony.project.account_touch.ui.viewmodel.ReceivedViewModel
import com.euphony.project.account_touch.utils.AssetsUtil
import com.euphony.project.account_touch.utils.model.UserIcon
import java.text.SimpleDateFormat

@Composable
fun ReceivedAccountsScreen(
    user: User?,
    receivedViewModel: ReceivedViewModel,
    onBackClick: () -> Unit,
) {
    val allReceived by receivedViewModel.allReceived.observeAsState()
    ReceivedAccounts(user, allReceived, onBackClick)
}

@Composable
fun ReceivedAccounts(
    user: User?,
    receiveds: List<Received>?,
    onBackClick: () -> Unit,
) {
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
                ReceivedAccountsUser(user)
                ReceivedAccountItems(receiveds)
            }
        }
    )
}

@Composable
fun ReceivedAccountsUser(user: User?) {
    val imageBitmap =
        if (user == null) null else AssetsUtil.getBitmap(LocalContext.current, user.icon.path)

    Row(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${user?.nickname}님께서\n받으신 계좌입니다.",
            color = Blue_6D95FF,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        UserIconItem(imageBitmap, Blue_6D95FF)
    }
}

@Composable
fun ReceivedAccountItems(receiveds: List<Received>?) {
    val _receiveds = receiveds ?: listOf()

    LazyColumn {
        items(_receiveds.size) {
            ReceivedAccountItem(_receiveds[it])
        }
    }
}

@Composable
fun ReceivedAccountItem(received: Received) {
    val context = LocalContext.current
    val imageBitmap = AssetsUtil.getBitmap(context, "banks/bnk_bank.png")
    val yyyyMMdd = SimpleDateFormat("yyyy.MM.dd").format(received.createDate)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Gray_F4F4F4,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                BankImage(imageBitmap = imageBitmap)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = received.accountNumber,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp),
                        text = yyyyMMdd,
                        color = Gray_9C9C9C,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}