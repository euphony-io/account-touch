package com.euphony.project.account_touch.ui.screen.userregister

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.content.ContextCompat
import com.euphony.project.account_touch.ui.screen.main.MainActivity
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme
import com.euphony.project.account_touch.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRegisterActivity : ComponentActivity() {

    private val userViewModel by viewModels<UserViewModel>()

    private val requestPermissionCallback =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Toast.makeText(this, "마이크 권한 허용 승인", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "마이크 권한을 반드시 허용해야 합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:$packageName")
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AccounttouchTheme {
                if (userViewModel.user.observeAsState().value != null) {
                    startMainActivity()
                }
                UserRegisterScreen(
                    userViewModel,
                    onStartMainActivity = { startMainActivity() },
                    onUserNicknameInvalid = {
                        Toast.makeText(this, "유저 이름은 1자 이상 15자 이하로 입력해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

        when {
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED -> {
                // TODO: Euphony listen
            }
            shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO) -> {
                requestPermissionCallback.launch(Manifest.permission.RECORD_AUDIO)
            }
            else -> {
                requestPermissionCallback.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }
}
