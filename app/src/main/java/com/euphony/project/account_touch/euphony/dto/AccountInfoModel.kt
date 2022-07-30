package com.euphony.project.account_touch.euphony.dto

import com.euphony.project.account_touch.utils.model.UserIcon
import com.google.gson.annotations.SerializedName

data class AccountInfoModel(
    @SerializedName("accountNumber") val accountNumber: String,
    @SerializedName("accountNickname") val accountNickname: String,
    @SerializedName("speakerNickName") val speakerNickName: String,
    @SerializedName("speakerIcon") val speakerIcon: UserIcon,
    @SerializedName("bank_id") val bank_id: Long,
)