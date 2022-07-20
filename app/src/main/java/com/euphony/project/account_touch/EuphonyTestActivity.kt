package com.euphony.project.account_touch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme

class EuphonyTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AccounttouchTheme {
                Column {
                    Button(onClick = {}) {
                        Text(text = "transmit")
                    }
                    Button(onClick = {}) {
                        Text(text = "receive")
                    }
                }
            }
        }
    }
}
