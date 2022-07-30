package com.euphony.project.account_touch.euphony.dto

import com.google.gson.annotations.SerializedName

data class InfoModel (
    @SerializedName("userInfo") val userInfo: UserInfoModel,
    @SerializedName("accountInfo") val accountInfo: AccountInfoModel? = null,
)