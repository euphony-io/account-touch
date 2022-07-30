package com.euphony.project.account_touch.euphony.dto

import com.google.gson.annotations.SerializedName

data class InfoModel (
    @SerializedName("id") val id: Int,
    @SerializedName("num") val num: String
)