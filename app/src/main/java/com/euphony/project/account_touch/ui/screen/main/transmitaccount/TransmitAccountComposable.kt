package com.euphony.project.account_touch.ui.screen.main.transmitaccount

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.global.AccountWithBank
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.ui.screen.common.UserIconItem
import com.euphony.project.account_touch.ui.screen.userregister.space
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.Blue_DFE8FF
import com.euphony.project.account_touch.ui.theme.Gray_9C9C9C
import com.euphony.project.account_touch.ui.theme.Gray_D2D2D2
import com.euphony.project.account_touch.ui.theme.Gray_F4F4F4
import com.euphony.project.account_touch.ui.theme.White_FAF5F5
import com.euphony.project.account_touch.utils.AssetsUtil
import com.euphony.project.account_touch.utils.model.Color
import com.euphony.project.account_touch.utils.model.UserIcon

@Composable
fun TransmitAccountScreen(
    account: AccountWithBank?,
    user: User?,
    onBackClick: () -> Unit
) {
    TransmitAccount(user, account, onBackClick)
}

@Composable
fun TransmitAccount(user: User?, account: AccountWithBank?, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                backgroundColor = colorResource(id = R.color.white),
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
            space(value = 50)
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                TransmitAccountInfo(user, account)
                TransmitImage()
                //ddd
            }
        }
    )
}

@Composable
fun TransmitAccountInfo(user: User?, accountWithBank: AccountWithBank?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${user?.nickname}의\n${accountWithBank?.account?.nickname} 계좌를\n전송합니다.",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            color = Blue_6D95FF
        )
    }
}

@Composable
fun TransmitImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.transmit),
            contentDescription = "전송 이미지",
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Receivers(receivers: List<User>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(receivers.size) {
            ReceiverItem(receivers[it])
        }
    }
}

@Composable
fun ReceiverItem(user: User) {
    val imageBitmap = AssetsUtil.getBitmap(LocalContext.current, user.icon.path)

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Gray_F4F4F4
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                UserIconItem(imageBitmap = imageBitmap, color = Blue_DFE8FF)
            }
            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp),
                text = user.nickname,
                color = Blue_6D95FF,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = "5M 내",
                color = Gray_9C9C9C,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}