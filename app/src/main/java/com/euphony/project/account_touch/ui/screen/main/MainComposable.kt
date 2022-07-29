package com.euphony.project.account_touch.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.global.AccountWithBank
import com.euphony.project.account_touch.ui.screen.main.model.Content
import com.euphony.project.account_touch.ui.screen.userregister.LoadText
import com.euphony.project.account_touch.ui.screen.userregister.ProfileImage
import com.euphony.project.account_touch.ui.screen.userregister.space
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white
import com.euphony.project.account_touch.ui.viewmodel.AccountViewModel
import com.euphony.project.account_touch.utils.AssetsUtil
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainBottomSheetScreen(
    accountViewModel: AccountViewModel,
    onReceivedIconClick: () -> Unit,
    onAccountClick: () -> Unit,
) {
    val accounts = accountViewModel.accounts.observeAsState()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var isEditClicked by remember { mutableStateOf(false) }
    var content by remember { mutableStateOf(Content.CHOOSE_BANK) }

    ModalBottomSheetLayout(
        sheetContent = {
            when (content) {
                Content.CHOOSE_BANK -> {
                    ChooseBankScreen(
                        onCloseClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                        },
                        onBankItemClick = { content = Content.ADD_ACCOUNT }
                    )
                }
                Content.ADD_ACCOUNT -> {
                    AccountInfoScreen(
                        isEditClicked = isEditClicked,
                        onCloseClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                        },
                        onEditClick = { isEditClicked = !isEditClicked },
                        isAddContent = true
                    )
                }
                Content.UPDATE_ACCOUNT -> {
                    AccountInfoScreen(
                        isEditClicked = isEditClicked,
                        onCloseClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                        },
                        onEditClick = { isEditClicked = !isEditClicked },
                        isAddContent = false
                    )
                }
            }

        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White,
    ) {
        LoadMainView(
            accounts.value,
            onAddButtonClick = {
                isEditClicked = false
                content = Content.CHOOSE_BANK
                coroutineScope.launch {
                    modalBottomSheetState.show()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    LoadMainView(
        listOf(),
        onAddButtonClick = {}
    )
}

@Composable
fun LoadMainView(accounts: List<AccountWithBank>?, onAddButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 40.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(painter = painterResource(id = R.drawable.ic_alarm), contentDescription = "알람 아이")
        }
        Row {
            var nickname: String = "임시 닉네임"
            LoadText(str = "$nickname 님, \n안녕하세요.")
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                ProfileImage(
                    profile = painterResource(id = R.drawable.ic_profile_smile),
                    width = 120, height = 120, color = mainColor)
            }
        }
        myAccountList(accounts)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onAddButtonClick() }) {
                Text(text = "OPEN BOTTOM SHEET")
            }
        }
    }
}

@Composable
fun myAccountList(accounts: List<AccountWithBank>?) {
    val _accounts = accounts ?: listOf()

    LazyColumn {
        items(_accounts.size) {
            myAccountItem(_accounts[it])
            space(20)
        }
    }
}

@Composable
fun myAccountItem(accountWithBank: AccountWithBank) {
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

                //계좌별 공유 방식 아이콘
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    ShareImage(accountWithBank.account.isAlwaysOn)
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
fun ShareImage(isAlways: Boolean) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(white),
        contentAlignment = Alignment.Center
    ) {
        if (isAlways)
            Image(painterResource(id = R.drawable.ic_on), "")
        else
            Image(painterResource(id = R.drawable.ic_share), "")
    }
}
