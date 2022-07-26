package com.euphony.project.account_touch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccounttouchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, top = 100.dp),
                    color = MaterialTheme.colors.background
                ) {
                    loadingMainView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun loadingMainView(){
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
//    ){
//        GreetingText(name = "임시 닉네임")
//        Image(painterResource(id = R.drawable.ic_alarm), contentDescription = "", Alignment.Center)
//    }

    AccounttouchTheme {
        GreetingText(name = "임시 닉네임")
    }
}

@Composable
fun GreetingText(name: String) {
    Text(text = "$name 님, \n안녕하세요.")
}