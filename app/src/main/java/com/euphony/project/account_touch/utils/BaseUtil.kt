package com.euphony.project.account_touch.utils

import java.util.*


object BaseUtil {
    fun base64Encoding(encrypted: ByteArray): ByteArray? = Base64.getEncoder().encode(encrypted)
}
