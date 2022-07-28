package com.euphony.project.account_touch.ui.screen.main

import com.euphony.project.account_touch.utils.model.UserIcon
import java.util.*

data class User(
    val nickname: String,
    val icon: UserIcon,
)

data class Received(
    val accountNickname: String,
    val accountNumber: String,
    val speakerNickName: String,
    val speakerIcon: UserIcon,
    val createDate: Date = Date(),
    val modifyDate: Date = Date()
)

data class Bank(
    val id: Long,
)

