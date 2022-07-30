package com.euphony.project.account_touch.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlarmOn
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.data.global.AccountWithBank
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.ui.screen.common.UserIconItem
import com.euphony.project.account_touch.ui.screen.main.model.Content
import com.euphony.project.account_touch.ui.screen.userregister.space
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white
import com.euphony.project.account_touch.ui.viewmodel.AccountViewModel
import com.euphony.project.account_touch.utils.AssetsUtil
import com.euphony.project.account_touch.utils.model.UserIcon
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainBottomSheetScreen(
    user: User,
    accounts: List<AccountWithBank>,
    accountViewModel: AccountViewModel,
    banks: List<Bank>,
    onReceivedIconClick: () -> Unit,
    onAccountClick: (Int) -> Unit,
    onAddAccountInValid: () -> Unit,
    onModifyAccountInValid: () -> Unit,
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var isEditClicked by remember { mutableStateOf(false) }
    var content by remember { mutableStateOf(Content.CHOOSE_BANK) }
    var bankIndex by remember { mutableStateOf(0) }

    ModalBottomSheetLayout(
        sheetContent = {
            when (content) {
                Content.CHOOSE_BANK -> {
                    ChooseBankScreen(
                        banks,
                        onCloseClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                        },
                        onBankItemClick = { it ->
                            bankIndex = it
                            content = Content.ADD_ACCOUNT
                        }
                    )
                }
                Content.ADD_ACCOUNT -> {
                    AccountInfoScreen(
                        banks[bankIndex],
                        isEditClicked = isEditClicked,
                        onCloseClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                        },
                        onEditClick = { isEditClicked = !isEditClicked },
                        isAddContent = true,
                        accountViewModel,
                        onAddAccountInValid = { onAddAccountInValid() },
                        onModifyAccountInValid = { onModifyAccountInValid() }
                    )
                }
                Content.UPDATE_ACCOUNT -> {
                    AccountInfoScreen(
                        banks[bankIndex],
                        isEditClicked = isEditClicked,
                        onCloseClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                        },
                        onEditClick = { isEditClicked = !isEditClicked },
                        isAddContent = false,
                        accountViewModel,
                        onAddAccountInValid = { onAddAccountInValid() },
                        onModifyAccountInValid = { onModifyAccountInValid() }
                    )
                }
            }

        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White,
    ) {
        LoadMainView(
            accounts,
            user,
            onReceivedIconClick,
            onAddButtonClick = {
                isEditClicked = false
                content = Content.CHOOSE_BANK
                coroutineScope.launch {
                    modalBottomSheetState.show()
                }
            },
            onAccountClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    LoadMainView(
        listOf(),
        user = User(nickname = "kim", icon = UserIcon.GHOST),
        onReceivedIconClick = {},
        onAddButtonClick = {},
        onAccountClick = {}
    )
}

@Composable
fun LoadMainView(
    accounts: List<AccountWithBank>,
    user: User,
    onReceivedIconClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onAccountClick: (Int) -> Unit,
) {
    val imageBitmap = AssetsUtil.getBitmap(LocalContext.current, user.icon.path)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = { onReceivedIconClick() }) {
                Icon(
                    imageVector = Icons.Filled.AlarmOn,
                    contentDescription = "수신 계좌",
                    tint = Blue_6D95FF
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${user.nickname}님, \n안녕하세요.",
                color = Blue_6D95FF,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            UserIconItem(imageBitmap, Blue_6D95FF)
        }

        //내 계좌 리스트 불러오기
        MyAccountList(accounts, onAccountClick)

        //추가 버튼
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = mainColor, contentColor = white
            ),
            onClick = {
                onAddButtonClick()
            }
        ) {
            Text(text = "+", Modifier.size(20.dp))
        }
    }
}

@Composable
fun MyAccountList(accounts: List<AccountWithBank>, onAccountClick: (Int) -> Unit) {
    LazyColumn() {
        items(accounts.size) {
            MyAccountItem(accounts[it], it, onAccountClick)
            space(20)
        }
    }
}

@Composable
fun MyAccountItem(accountWithBank: AccountWithBank, index: Int, onAccountClick: (Int) -> Unit) {
    val bankImgBitmap =
        AssetsUtil.getBitmap(LocalContext.current, accountWithBank.bank.bankIconPath.path)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = colorResource(id = accountWithBank.account.color.colorId),
        elevation = 5.dp,
    ) {
        Row {
            Row(
                Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //bank Icon
                BankImage(imageBitmap = bankImgBitmap)

                Column(
                    Modifier.padding(start = 15.dp)
                ) {
                    Text(
                        "${accountWithBank.account.nickname}의 ${accountWithBank.bank.name} 계좌",
                        fontSize = 18.sp,
                        color = colorResource(id = accountWithBank.account.color.fontColorId)
                    )
                    Text(
                        accountWithBank.account.accountNumber,
                        fontSize = 18.sp,
                        color = colorResource(id = accountWithBank.account.color.fontColorId)
                    )
                }

                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    ShareImage(index, onAccountClick)
                }
            }
        }
    }
}

//은행 이미지뷰
@Composable
fun BankImage(imageBitmap: ImageBitmap?) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(shape = CircleShape)
            .background(white),
        contentAlignment = Alignment.Center
    ) {
        if (imageBitmap != null)
            Image(bitmap = imageBitmap, contentDescription = "")
    }
}

//계좌 공유 아이콘 이미지뷰
@Composable
fun ShareImage(index: Int, onAccountClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onAccountClick(index) }
            .background(white),
        contentAlignment = Alignment.Center
    ) {
        Image(painterResource(id = R.drawable.ic_share), "계좌 공유 아이콘")
    }
}
