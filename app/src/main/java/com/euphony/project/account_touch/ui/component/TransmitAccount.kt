package com.euphony.project.account_touch.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.euphony.project.account_touch.data.entity.Account
import com.euphony.project.account_touch.data.entity.User
import com.euphony.project.account_touch.data.entity.model.Color
import com.euphony.project.account_touch.data.entity.model.UserIcon

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
