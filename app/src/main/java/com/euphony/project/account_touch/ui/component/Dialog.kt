package com.euphony.project.account_touch.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.ui.screen.loadText
import com.euphony.project.account_touch.ui.screen.space
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    TransmitDialog("계좌 보내기 성공")
}

@Composable
fun TransmitDialog(str:String){
    Dialog(onDismissRequest = {}) {
        Surface(
            modifier = Modifier
                .width(200.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            color = white
        ){
            successContent(str)
        }
    }
}

@Composable
fun successContent(str:String){
    Column(
        modifier = Modifier
            .padding(vertical = 30.dp),
        verticalArrangement =  Arrangement.Center
    ){
        loadText("${str}")
        space(value = 20)
        TransmitImage()
        space(20)
        TextRound("닉네임 님")
        Row(Modifier.padding(end = 5.dp)){
            TextRound("송신자님")
            TextRound(str = "수신자님")
        }

        //확인 버튼
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
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ){
        Text("${str}")
    }
}
