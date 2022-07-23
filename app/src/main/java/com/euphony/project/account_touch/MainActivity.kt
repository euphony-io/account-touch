package com.euphony.project.account_touch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                    modifier = Modifier.fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, top = 100.dp),
                    color = MaterialTheme.colors.background
                ) {
                    loadingView()
                }
            }
        }
    }
}

@Composable
fun GreetingText(name: String) {
    Text(text = "$name 님, \n안녕하세요.")
}

@Preview(showBackground = true)
@Composable
fun loadingView(){
    AccounttouchTheme {
        GreetingText(name = "임시 닉네임")
    }
}
