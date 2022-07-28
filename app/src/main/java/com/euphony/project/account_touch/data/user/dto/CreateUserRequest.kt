package com.euphony.project.account_touch.data.user.dto

import com.euphony.project.account_touch.data.global.base.request.BaseRequest
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.utils.model.UserIcon

class CreateUserRequest (
    val nickname: String,
    val userIcon: UserIcon,
) :BaseRequest {
    override fun validate(): Boolean {
        if(nickname.length > 15) return false
        return true
    }

    fun toEntity(): User{
        return User(0, nickname, userIcon)
    }
}