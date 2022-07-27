package com.euphony.project.account_touch.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.entity.Account
import com.euphony.project.account_touch.data.entity.User
import com.euphony.project.account_touch.data.entity.model.Color
import com.euphony.project.account_touch.data.entity.model.UserIcon
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.Blue_DFE8FF
import com.euphony.project.account_touch.ui.theme.Gray_9C9C9C
import com.euphony.project.account_touch.ui.theme.Gray_D2D2D2
import com.euphony.project.account_touch.ui.theme.Gray_F4F4F4
import com.euphony.project.account_touch.ui.theme.White_FAF5F5
import com.euphony.project.account_touch.utils.AssetsUtil

@Composable
fun TransmitAccountScreen() { // TODO: viewModel
    val user = User(
        nickname = "영욱",
        icon = UserIcon.HAPPY
    )
    val account = Account(
        nickname = "국민은행 체크카드",
        accountNumber = "123456789",
        isAllowAny = false,
        isAlwaysOn = false,
        color = Color.SKY
    )
    val receivers = listOf<User>(
        User(
            nickname = "나연",
            icon = UserIcon.PARTY
        ),
        User(
            nickname = "나연",
            icon = UserIcon.PARTY
        ),
        User(
            nickname = "나연",
            icon = UserIcon.PARTY
        ),
        User(
            nickname = "은빈",
            icon = UserIcon.SMILE
        ),
        User(
            nickname = "은빈",
            icon = UserIcon.SMILE
        ),
        User(
            nickname = "은빈",
            icon = UserIcon.SMILE
        ),
    )
    TransmitAccount(user, account, receivers)
}

@Composable
fun TransmitAccount(user: User, account: Account, receivers: List<User>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                backgroundColor = colorResource(id = R.color.white),
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
            Column {
                TransmitAccountInfo(user, account)
                TransmitAllButton()
                Receivers(receivers)
            }
        }
    )
}

@Composable
fun TransmitAccountInfo(user: User, account: Account) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${user.nickname}의\n${account.nickname} 계좌를\n전송합니다.",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            color = Blue_6D95FF
        )
    }
}

@Composable
fun TransmitAllButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Gray_D2D2D2
            )
        ) {
            Text(
                text = "모두에게 보내기",
                color = White_FAF5F5,
                fontSize = 10.sp
            )
        }
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

@Preview(showBackground = true)
@Composable
fun TransmitAccountPreview() {
    val user = User(
        nickname = "영욱",
        icon = UserIcon.HAPPY
    )
    val account = Account(
        nickname = "국민은행 체크카드",
        accountNumber = "123456789",
        isAllowAny = false,
        isAlwaysOn = false,
        color = Color.SKY
    )
    val receivers = listOf<User>(
        User(
            nickname = "나연",
            icon = UserIcon.PARTY
        ),
        User(
            nickname = "나연",
            icon = UserIcon.PARTY
        ),
        User(
            nickname = "나연",
            icon = UserIcon.PARTY
        ),
        User(
            nickname = "은빈",
            icon = UserIcon.SMILE
        ),
        User(
            nickname = "은빈",
            icon = UserIcon.SMILE
        ),
        User(
            nickname = "은빈",
            icon = UserIcon.SMILE
        ),
    )
    TransmitAccount(user, account, receivers)
}
