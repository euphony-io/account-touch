package com.euphony.project.account_touch.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.data.entity.Bank
import com.euphony.project.account_touch.ui.screen.userregister.LoadText
import com.euphony.project.account_touch.ui.screen.userregister.ProfileImage
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white

@Preview(showBackground = true)
@Composable
fun loadingMainView(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 40.dp)
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ){
            Image(painter = painterResource(id = R.drawable.ic_alarm), contentDescription = "")
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
                    width = 120, height = 120, color = mainColor
                )
            }
        }

        //내 계좌 리스트
        /*DB작업 - 유저가 등록한 계좌 리스트 불러오기
        val aList = ArrayList<Bank>()
        aList.add(Bank(1, "닉네임1", BankInfo.BNK,
            10, ExternalPackage.KOOKMIN,
            createDate = Date(2022,1,2),
            Date(2022,3,1)
        ))
        myAccountList(aList)
        */
    }
}

@Composable
fun myAccountList(accounts: List<Bank>){
    val scrollState = rememberScrollState()
    Column(
        Modifier.verticalScroll(scrollState)
    ){
//        items(accounts){ account ->
//            //data class에서 필요한 부분만 추출하여 호출
//            myAccountItem(name = "닉네임", bankName = "국민 은행", bankIcon = painterResource(id = R.drawable.ic_profile_twinkle), account = "123-123-123", bgColor = Purple200)
//        }
    }
}

@Composable
fun myAccountItem(name:String, bankName:String, account:String, bankIcon:Painter, bgColor:Color){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = bgColor,
        elevation = 5.dp,
    ){
        Row(){
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                contentAlignment = Alignment.Center
            ){
                AccountImage(profile = painterResource(id = R.drawable.ic_profile_smile))   //은행 아이콘넣기
            }
            Column {
                Text("$name 의 $bankName 계좌", color = white, fontSize = 18.sp)
                Text("$account", color = white, fontSize = 15.sp)
            }
        }
    }

}

//내 계좌 은행 이미지뷰 (흰배경 + 은행아이콘)
@Composable
fun AccountImage(
    profile: Painter,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .width(40.dp)
            .height(40.dp)
            .padding(start = 20.dp, top = 20.dp),
        shape = RoundedCornerShape(50.dp),
        backgroundColor = white,
        elevation = 5.dp,
    ){
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(painter = profile, contentDescription = "", alignment = Alignment.Center)
        }
    }
}
