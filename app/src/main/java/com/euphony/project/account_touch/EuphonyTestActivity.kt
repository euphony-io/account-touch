package com.euphony.project.account_touch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme

class EuphonyTestActivity : ComponentActivity() {

    private val viewModel by viewModels<EuphonyTestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AccounttouchTheme {
                Column {
                    TransmitButton()
                    ReceiveButton()
                }
            }
        }
    }

    @Composable
    private fun TransmitButton() {
        val isSpeaking = viewModel.isSpeaking.observeAsState()

        Button(onClick = {
            viewModel.speak()
        }) {
            if (isSpeaking.value == false) Text(text = "transmit")
            else Text(text = "cancel")
        }
    }

    @Composable
    private fun ReceiveButton() {
        val isListening = viewModel.isListening.observeAsState()

        Button(onClick = {
            viewModel.listen()
        }) {
            if (isListening.value == false) Text(text = "receive")
            else Text(text = "cancel")
        }
    }
}
