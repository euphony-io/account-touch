package com.euphony.project.account_touch.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.data.entity.User
import com.euphony.project.account_touch.data.entity.model.UserIcon
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.utils.AssetsUtil

@Composable
fun ReceivedAccountsScreen() { // TODO: viewModel, onBackClick as parameters
    // dummy data
    val user = User(
        nickname = "영욱",
        icon = UserIcon.CRYING,
    )
    ReceivedAccounts(user)
}

@Composable
fun ReceivedAccounts(user: User) {
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
        ReceivedAccountsUser(user)
    }
}

@Composable
fun ReceivedAccountsUser(user: User) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val imageBitmap = AssetsUtil.getBitmap(LocalContext.current, user.icon.path)

    Row(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .onSizeChanged { size = it },
            text = "${user.nickname}님께서\n받으신 계좌입니다.",
            color = Blue_6D95FF,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        println(size.height)
        Box(
            modifier = Modifier
                .then(
                    with(LocalDensity.current) {
                        Modifier.size(
                            width = size.height.toDp(),
                            height = size.height.toDp()
                        )
                    }
                )
                .clip(RoundedCornerShape(8.dp))
                .background(color = Blue_6D95FF),
            contentAlignment = Alignment.Center
        ) {
            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "유저 아이콘"
                )
            } else {
                Image(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "유저 아이콘 없음"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReceivedAccountsPreview() {
    val user = User(
        nickname = "영욱",
        icon = UserIcon.CRYING,
    )
    ReceivedAccounts(user)
}
