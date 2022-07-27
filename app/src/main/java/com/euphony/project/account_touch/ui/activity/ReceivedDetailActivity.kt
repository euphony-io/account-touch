package com.euphony.project.account_touch.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme

class ReceivedDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccounttouchTheme {}
        }
    }
}
