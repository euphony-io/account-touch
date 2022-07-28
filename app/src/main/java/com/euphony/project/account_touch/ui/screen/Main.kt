package com.euphony.project.account_touch.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.ui.component.Bank
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white
import java.util.*

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
           loadText(str = "$nickname 님, \n안녕하세요.")
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
    }
}

@Composable
fun myAccountList(accounts: List<Bank>){
    val scrollState = rememberScrollState()
    Column(
        Modifier.verticalScroll(scrollState)
    ){

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