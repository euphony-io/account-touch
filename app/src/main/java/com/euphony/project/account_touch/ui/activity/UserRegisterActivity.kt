package com.euphony.project.account_touch.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.euphony.project.account_touch.ui.component.UserRegisterScreen
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme

class UserRegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: 유저 생성 유무 로직
        setContent {
            AccounttouchTheme {
                UserRegisterScreen(
                    onNextClick = {
                        // TODO: 유저 생성
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}
