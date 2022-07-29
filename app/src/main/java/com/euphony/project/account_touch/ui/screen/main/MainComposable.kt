package com.euphony.project.account_touch.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.ui.screen.userregister.LoadText
import com.euphony.project.account_touch.ui.screen.userregister.ProfileImage
import com.euphony.project.account_touch.ui.screen.userregister.space
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white
import com.euphony.project.account_touch.utils.AssetsUtil
import java.util.*

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    LoadMainView()
}

@Composable
fun LoadMainView(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 40.dp)
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ){
            Image(painter = painterResource(id = R.drawable.ic_alarm), contentDescription = "알람 아이")
        }
        Row {
            var nickname:String = "임시 닉네임"
            LoadText(str = "$nickname 님, \n안녕하세요.")
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ){
                ProfileImage(
                    profile = painterResource(id = R.drawable.ic_profile_smile),
                    width = 120, height = 120, color = mainColor)
            }
        }

        //DB- 유저가 등록한 계좌 리스트 불러오기
        //Account Dummy Data
        val aList = ArrayList<Account>()
        aList.add(Account(1, 1, "user1", "123-123-123", true, color = com.euphony.project.account_touch.utils.model.Color.BLUE))
        aList.add(Account(2, 1, "user2", "123-456-123", false, color = com.euphony.project.account_touch.utils.model.Color.PINK))
        aList.add(Account(3, 1, "user3", "456-789-123", false, color = com.euphony.project.account_touch.utils.model.Color.INDIGO))
        myAccountList(aList)
    }
}

@Composable
fun myAccountList(accounts: List<Account>) {
    LazyColumn {
        items(accounts.size) {
            myAccountItem(accounts[it])
            space(20)
        }
    }
}

@Composable
fun myAccountItem(account: Account) {
    //Test Data
    var bankName = "국민" //account.bank의 id통해 가져와야 함
    var bankIconPath = "banks/kb_bank.png"
    var bgColor = account.color.colorId
    var fontColor = account.color.fontColorId
    val bankImgBitmap = AssetsUtil.getBitmap(LocalContext.current, bankIconPath)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = colorResource(id = bgColor),
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
                        "${account.nickname}의 ${bankName} 계좌",
                        fontSize = 18.sp,
                        color = colorResource(id = fontColor)
                    )
                    Text(
                        "${account.accountNumber}",
                        fontSize = 18.sp,
                        color = colorResource(id = fontColor)
                    )
                }

                //계좌별 공유 방식 아이콘
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    ShareImage(account.isAlwaysOn)
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
        if(imageBitmap != null)
            Image(bitmap = imageBitmap, contentDescription = "")
    }
}

//계좌 공유 아이콘 이미지뷰
@Composable
fun ShareImage(isAlways:Boolean){
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(white),
        contentAlignment = Alignment.Center
    ){
        if(isAlways)
            Image(painterResource(id = R.drawable.ic_on), "")
        else
            Image(painterResource(id = R.drawable.ic_share), "")
    }
}