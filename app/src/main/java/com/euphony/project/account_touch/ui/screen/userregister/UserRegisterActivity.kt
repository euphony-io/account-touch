package com.euphony.project.account_touch.ui.screen.userregister

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.euphony.project.account_touch.ui.screen.main.MainActivity
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme
import com.euphony.project.account_touch.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRegisterActivity : ComponentActivity() {

    private val userViewModel by viewModels<UserViewModel>()

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
}
