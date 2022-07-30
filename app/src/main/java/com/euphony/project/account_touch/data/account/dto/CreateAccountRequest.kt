package com.euphony.project.account_touch.data.account.dto

import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.global.base.request.BaseRequest
import com.euphony.project.account_touch.utils.model.Color

class CreateAccountRequest(
    val bank: Long,
    val nickname: String,
    val accountNumber: String,
    val color: Color
): BaseRequest {
    override fun validate(): Boolean {
        if(bank < 0) return false
        if(nickname.length > 10) return false
        return true
    }

    fun toEntity(): Account{
        return Account(0, bank, nickname, accountNumber, color)
    }
}