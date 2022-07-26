package com.euphony.project.account_touch.utils

import android.util.Log
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptionUtil {

    fun encrypt(data: ByteArray, key: ByteArray, ivs: ByteArray): ByteArray? {
        return try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val secretKeySpec = SecretKeySpec(key, "AES")
            val finalIvs = ByteArray(16)
            val len = if (ivs.size > 16) 16 else ivs.size
            System.arraycopy(ivs, 0, finalIvs, 0, len)
            val ivParamSpec = IvParameterSpec(finalIvs)
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParamSpec)
            cipher.doFinal(data)
        } catch (e: Exception) {
            Log.e("EncryptionUtil", e.message.toString())
            null
        }
    }

    fun decrypt(data: ByteArray, key: ByteArray, ivs: ByteArray): ByteArray? {
        return try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val secretKeySpec = SecretKeySpec(key, "AES")
            val finalIvs = ByteArray(16)
            val len = if (ivs.size > 16) 16 else ivs.size
            System.arraycopy(ivs, 0, finalIvs, 0, len)
            val ivParamSpec = IvParameterSpec(finalIvs)
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParamSpec)
            cipher.doFinal(data)
        } catch (e: Exception) {
            Log.e("EncryptionUtil", e.message.toString())
            null
        }
    }
}
