package com.euphony.project.account_touch.utils

import com.euphony.project.account_touch.euphony.dto.AccountInfoModel
import com.euphony.project.account_touch.euphony.dto.UserInfoModel
import com.google.gson.Gson

object FormatUtil {

    val gson = Gson()

    fun userInfoToJson(userInfo: UserInfoModel): String{
        return gson.toJson(userInfo)
    }

    fun jsonToUserInfo(json: String): UserInfoModel {
        return gson.fromJson(json, UserInfoModel::class.java)
    }

    fun accountInfoToJson(key: String, accountInfo: AccountInfoModel): String{
        val data = gson.toJson(accountInfo)
        return "@" + EncryptionUtil.encrypt(
            data.toByteArray(),
            key.toByteArray(),
            key.toByteArray()
        ).toString();
    }

    fun accountInfoToJson(accountInfo: AccountInfoModel): String{
        return gson.toJson(accountInfo)
    }

    fun jsonToAccountInfo(json: String): AccountInfoModel {
        return gson.fromJson(json, AccountInfoModel::class.java)
    }

    fun jsonToAccountInfo(key: String, json: String): AccountInfoModel {
        val data = EncryptionUtil.decrypt(
            json.toByteArray(),
            key.toByteArray(),
            key.toByteArray()
        ).toString();

        return gson.fromJson(data, AccountInfoModel::class.java)
    }
}