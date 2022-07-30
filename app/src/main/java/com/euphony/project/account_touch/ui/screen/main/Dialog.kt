package com.euphony.project.account_touch.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.ui.screen.main.transmitaccount.TransmitImage
import com.euphony.project.account_touch.ui.screen.userregister.LoadText
import com.euphony.project.account_touch.ui.screen.userregister.space
import com.euphony.project.account_touch.ui.theme.grey
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    TransmitResultDialog(str = "성공")   //or 실패
}

@Composable
fun TransmitResultDialog(str:String){
    Dialog(onDismissRequest = {}) {
        Surface(
            modifier = Modifier
                .width(200.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(15.dp),
            color = white
        ){
            ResultContent(str)
        }
    }
}

@Composable
fun ResultContent(str:String){
    Column(
        modifier = Modifier
            .padding(vertical = 30.dp),
        verticalArrangement =  Arrangement.Center,
        Alignment.CenterHorizontally
    ){
        LoadText("계좌 보내기 ${str}")
        space(value = 10)

        TransmitImage()
        space(20)

        TextRound("닉네임 님")
        space(value = 5)
        Row(Modifier.padding(horizontal = 10.dp)){
            TextRound("송신자님")
            Spacer(Modifier.width(5.dp))
            TextRound("수신자님")
        }

        //확인 버튼
        space(value = 20)
        Button(
            onClick = {
                      //다이얼로그 닫기 작업
            },
            modifier = Modifier
                .fillMaxWidth(0.3f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = mainColor
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "확인",
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TextRound(str:String){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(grey)
            .padding(vertical = 5.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center,
    ){
        Text("${str}", color = mainColor, fontSize = 12.sp)
    }
}
