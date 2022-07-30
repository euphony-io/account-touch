package com.euphony.project.account_touch.euphony.dto

import com.google.gson.annotations.SerializedName

data class InfoModel (
    @SerializedName("id") val id: Long,
    @SerializedName("num") val num: String
)