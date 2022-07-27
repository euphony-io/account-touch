package com.euphony.project.account_touch.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.entity.Account
import com.euphony.project.account_touch.data.entity.User
import com.euphony.project.account_touch.data.entity.model.Color
import com.euphony.project.account_touch.data.entity.model.UserIcon
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF

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
    TransmitAccount(user, account)
}

@Composable
fun TransmitAccount(user: User, account: Account) {
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

            }
        }
    )
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
    TransmitAccount(user, account)
}
