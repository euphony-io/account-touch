package com.euphony.project.account_touch.ui.screen.userregister

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.user.dto.CreateUserRequest
import com.euphony.project.account_touch.ui.theme.grey
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white
import com.euphony.project.account_touch.ui.viewmodel.UserViewModel
import com.euphony.project.account_touch.utils.model.UserIcon
import kotlinx.coroutines.launch

@Composable
fun UserRegisterScreen(
    userViewModel: UserViewModel,
    onStartMainActivity: () -> Unit,
    onUserNicknameInvalid: () -> Unit,
) {
    var userNickname by remember { mutableStateOf("") }
    var userIcon by remember { mutableStateOf(UserIcon.GHOST) }

    UserRegisterContent(
        userNickname,
        onUserNicknameChange = {
            userNickname = it
        },
        onNextClick = {
            val createUserRequest = CreateUserRequest(userNickname, userIcon)
            if (createUserRequest.validate()) {
                userViewModel.addUser(createUserRequest)
                onStartMainActivity()
            } else {
                onUserNicknameInvalid()
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserRegisterContent(
    userNickname: String,
    onUserNicknameChange: (String) -> Unit,
    onNextClick: () -> Unit,
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            UserRegisterBottomSheetContent(
                onCloseClick = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                },
                onConfirmClick = { // TODO: 유저 아이콘 변경
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        LoadingUserRegisterView(
            userNickname,
            onUserNicknameChange,
            onUserIconClick = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            },
            onNextClick
        )
    }
}

@Composable
fun UserRegisterBottomSheetContent(
    onCloseClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    Column {
        Row(Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)) {
            Text(
                "이모지 설정",
                color = mainColor,
                textAlign = TextAlign.Left,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Image(
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                        .clickable(onClick = { onCloseClick() }),
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "닫기",
                )
            }
        }
        space(20)

        //유저 현재 이모지
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            ProfileImage(
                profile = painterResource(id = R.drawable.ic_profile_smile),
                width = 120, height = 120, color = mainColor
            )
        }
        space(20)

        //이모지 리스트
        BottomSheetListItem()
        space(20)

        //확인 버튼
        Button(
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = mainColor, contentColor = white
            ),
            onClick = { onConfirmClick() }
        ) {
            Text(text = "확인")
        }

        space(value = 20)
    }
}

@Composable
fun space(value: Int) {
    Spacer(modifier = Modifier.height(value.dp))
}

@Composable
fun LoadingUserRegisterView(
    userNickname: String,
    onUserNicknameChange: (String) -> Unit,
    onUserIconClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 100.dp, bottom = 50.dp)
    ) {
        LoadText(str = stringResource(R.string.user_register_str))
        space(50)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onUserIconClick() },
            contentAlignment = Alignment.Center) {
            ProfileImage(
                profile = painterResource(id = R.drawable.ic_profile_smile),
                width = 120, height = 120, color = mainColor
            )
        }
        space(100)
        NicknameEditText(
            userNickname,
            onUserNicknameChange
        )
        space(value = 30)
        NextButton(onNextClick = onNextClick)
    }
}

@Composable
fun LoadText(str: String) {
    Text(
        str,
        color = mainColor,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun NicknameEditText(
    userNickname: String,
    onUserNicknameChange: (String) -> Unit,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(8.dp),
        value = userNickname,
        onValueChange = { onUserNicknameChange(it) },
        singleLine = true,
        label = { Text("닉네임", color = mainColor, fontSize = 12.sp) })
}

@Composable
fun NextButton(onNextClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = mainColor, contentColor = white
        ),
        onClick = { onNextClick() }) {
        Text(text = "다음")
    }
}

//프로필 bg+icon 중첩 이미지 뷰
@Composable
fun ProfileImage(
    profile: Painter,
    width: Int, height: Int,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(width.dp)
            .height(height.dp)
            .padding(start = 20.dp, top = 20.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = color,
        elevation = 5.dp,
    ) {
        Box(
            modifier = modifier.height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(60.dp), painter = profile, contentDescription = "", alignment = Alignment.Center)
        }
    }
}

@Composable
fun BottomSheetListItem() {
    val context = LocalContext.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_party,
                onItemClick = { icon ->
                    Toast.makeText(
                        context, "파티 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_sad,
                onItemClick = { icon ->
                    Toast.makeText(
                        context, "슬픔 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_smile,
                onItemClick = { icon ->
                    Toast.makeText(
                        context, "미소 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_sunglasses,
                onItemClick = { icon ->
                    Toast.makeText(
                        context, "선글라스 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_pow,
                onItemClick = { icon ->
                    Toast.makeText(
                        context, "응가 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_twinkle,
                onItemClick = { icon ->
                    Toast.makeText(
                        context, "별 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_ghost,
                onItemClick = { icon ->
                    Toast.makeText(
                        context, "유령 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_laugh,
                onItemClick = { icon ->
                    Toast.makeText(
                        context, "웃는 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
        }
    }
}

@Composable
fun BottomSheetImage(
    Imoticon: Int,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var width = 100
    var height = 100
    Card(
        modifier = modifier
            .width(width.dp)
            .height(height.dp)
            .padding(start = 20.dp, top = 20.dp)
            .clickable(onClick = { onItemClick(Imoticon) }),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = grey,
        elevation = 5.dp,
    ) {
        Box(
            modifier = modifier.height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = Imoticon),
                contentDescription = "",
                alignment = Alignment.Center)
        }
    }
}
