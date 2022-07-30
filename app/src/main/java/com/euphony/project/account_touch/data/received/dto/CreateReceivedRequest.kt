package com.euphony.project.account_touch.data.received.dto

import com.euphony.project.account_touch.data.global.base.request.BaseRequest
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.utils.model.UserIcon

class CreateReceivedRequest(
    private val bankId: Long,
    private val accountNumber: String
): BaseRequest{
    override fun validate(): Boolean {
        if(bankId < 0) return false
        return true
    }

    fun toEntity(): Received{
        return Received(0L, bankId, accountNumber)
    }
}