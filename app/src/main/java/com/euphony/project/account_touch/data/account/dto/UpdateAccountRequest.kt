package com.euphony.project.account_touch.data.account.dto

import com.euphony.project.account_touch.data.global.base.request.BaseRequest
import com.euphony.project.account_touch.utils.model.Color

class UpdateAccountRequest(
    val id: Long,
    val color: Color
): BaseRequest {
    override fun validate(): Boolean {
        if(id < 0) return false
        return true
    }
}