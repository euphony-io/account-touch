package com.euphony.project.account_touch.utils

import com.euphony.project.account_touch.euphony.dto.InfoModel
import com.google.gson.Gson

object FormatUtil {

    private val gson = Gson()

    fun infoToJson(infoModel: InfoModel): String{
        return gson.toJson(infoModel)
    }

    fun jsonToInfo(json: String): InfoModel {
        return gson.fromJson(json, InfoModel::class.java)
    }
}