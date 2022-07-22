package com.euphony.project.account_touch

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.content.ContextCompat
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme

class EuphonyTestActivity : ComponentActivity() {

    private val viewModel by viewModels<EuphonyTestViewModel>()
    private val requestPermissionCallback =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                viewModel.listen(EuphonyManager.getEuTxManager(), EuphonyManager.getEuRxManager())
            } else {
                // do nothing
            }
        }

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
            viewModel.speak(EuphonyManager.getEuTxManager())
        }) {
            if (isSpeaking.value == false) Text(text = "transmit")
            else Text(text = "cancel")
        }
    }

    @Composable
    private fun ReceiveButton() {
        val isListening = viewModel.isListening.observeAsState()

        Button(onClick = {
            when {
                ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED -> {
                    viewModel.listen(EuphonyManager.getEuTxManager(), EuphonyManager.getEuRxManager())
                }
                shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO) -> requestPermissionCallback.launch(
                    Manifest.permission.RECORD_AUDIO)
                else -> requestPermissionCallback.launch(Manifest.permission.RECORD_AUDIO)
            }
        }) {
            if (isListening.value == false) Text(text = "receive")
            else Text(text = "cancel")
        }
    }
}
