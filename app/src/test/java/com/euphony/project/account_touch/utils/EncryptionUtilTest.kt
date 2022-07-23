package com.euphony.project.account_touch.utils

import org.junit.Assert.*
import org.junit.Test


class EncryptionUtilTest {

    @Test
    fun 데이터를_암호화한다() {
        // Given
        val plain = "euphony".toByteArray()
        val key = "1234567812345678".toByteArray()
        val ivs = "1234567812345678".toByteArray()

        // When
        val encryptedResult = EncryptionUtil.encrypt(plain, key, ivs)
        val encodedResult = BaseUtil.base64Encoding(encryptedResult!!)

        assertEquals(String(encodedResult!!), "HHb8+2aixCT63Deat3qEJg==")
    }

    @Test
    fun 데이터를_복호화한다() {
        // Given
        val encodedString = "HHb8+2aixCT63Deat3qEJg=="
        val key = "1234567812345678".toByteArray()
        val ivs = "1234567812345678".toByteArray()

        // When
        val decodedResult = BaseUtil.base64Decoding(encodedString)
        val decryptedResult = EncryptionUtil.decrypt(decodedResult, key, ivs)

        assertEquals(String(decryptedResult!!), "euphony")

    }
}
