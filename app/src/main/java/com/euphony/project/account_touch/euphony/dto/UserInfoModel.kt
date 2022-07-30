package com.euphony.project.account_touch.euphony.dto

import com.euphony.project.account_touch.utils.model.UserIcon
import com.google.gson.annotations.SerializedName

data class UserInfoModel(
    @SerializedName("key") val key: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("icon") val icon: UserIcon
)
