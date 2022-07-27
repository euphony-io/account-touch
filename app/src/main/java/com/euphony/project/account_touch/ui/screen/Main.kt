package com.euphony.project.account_touch.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.entity.Account
import com.euphony.project.account_touch.data.entity.User
import com.euphony.project.account_touch.data.entity.model.UserIcon
import com.euphony.project.account_touch.ui.component.ReceivedAccountsUser
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.transparent
import com.euphony.project.account_touch.ui.theme.white
import com.euphony.project.account_touch.utils.AssetsUtil
import kotlin.collections.ArrayList

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    loadingMainView()
}

@Composable
fun loadingMainView(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd,
        ){
            space(20)
            Image(painter = painterResource(id = R.drawable.ic_alarm),
                contentDescription = "",
                Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
        }

        val user = User(nickname = "나연", icon = UserIcon.HAPPY)
        ReceivedAccountsUser(user, "님,\n안녕하세요.")
        space(20)

        //DB- 유저가 등록한 계좌 리스트 불러오기
        //Account Dummy Data
        val aList = ArrayList<Account>()
        aList.add(Account(1, 1, "user1", "123-123-123", true, false, color = com.euphony.project.account_touch.data.entity.model.Color.BLUE))
        aList.add(Account(2, 1, "user2", "123-456-123", false, true, color = com.euphony.project.account_touch.data.entity.model.Color.PINK))
        aList.add(Account(3, 1, "user3", "456-789-123", false, false, color = com.euphony.project.account_touch.data.entity.model.Color.PINK))
        myAccountList(aList)

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

            }){
            Text(text = "+", Modifier.size(20.dp))
        }
    }
}

@Composable
fun myAccountList(accounts: List<Account>){
    
    LazyColumn(){
        items(accounts.size){
            myAccountItem(accounts[it])
            space(20)
        }
    }
}

@Composable
fun myAccountItem(account: Account){
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
    ){
        Row(
            Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            //bank Icon
            BankImage(imageBitmap = bankImgBitmap)

            Column(
                Modifier.padding(start = 15.dp)
            ){
                Text("${account.nickname}의 ${bankName} 계좌", fontSize = 18.sp, color = colorResource(id=fontColor))
                Text("${account.accountNumber}", fontSize = 18.sp, color = colorResource(id = fontColor))
            }

            //계좌별 공유 방식 아이콘
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                ShareImage(account.isAlwaysOn)
            }
        }

    }

}

//내 계좌 은행 이미지뷰 (흰배경 + 은행아이콘)
@Composable
fun BankImage(imageBitmap: ImageBitmap?) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(shape = CircleShape)
            .background(white),
        contentAlignment = Alignment.Center
    ){
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
