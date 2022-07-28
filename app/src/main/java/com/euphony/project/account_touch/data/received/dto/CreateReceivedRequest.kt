package com.euphony.project.account_touch.data.received.dto

import com.euphony.project.account_touch.data.global.base.request.BaseRequest
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.utils.model.UserIcon

class CreateReceivedRequest(
    private val bankId: Long,
    private val accountNickname: String,
    private val accountNumber: String,
    private val speakerNickname: String,
    private val speakerIcon: UserIcon
): BaseRequest{
    override fun validate(): Boolean {
        if(bankId < 0) return false
        if(accountNickname.isEmpty()) return false
        if(accountNumber.isEmpty()) return false
        if(speakerNickname.isEmpty()) return false
        return true
    }

    fun toEntity(): Received{
        return Received(0L, bankId, accountNickname, accountNumber,speakerNickname, speakerIcon)
    }
}