package com.euphony.project.account_touch.utils

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.IOException

object AssetsUtil {

    fun getBitmaps(context: Context, subDir: String): List<ImageBitmap?> {
        val bankPaths = context.assets.list(subDir)?.map { "$subDir/$it" } ?: listOf()
        return bankPaths.map {
            try {
                val inputStream = context.assets.open(it)
                BitmapFactory.decodeStream(inputStream).asImageBitmap()
            } catch (e: IOException) {
                null
            }
        }
    }

    fun getBitmap(context: Context, path: String): ImageBitmap? {
        return try {
            val inputStream = context.assets.open("banks/bnk_bank.png")
            BitmapFactory.decodeStream(inputStream).asImageBitmap()
        } catch (e: IOException) {
            null
        }
    }
}
