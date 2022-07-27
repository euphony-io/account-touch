package com.euphony.project.account_touch.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

@Composable
fun UserIconItem(imageBitmap: ImageBitmap?, color: Color) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        if (imageBitmap != null) {
            Image(
                modifier = Modifier
                    .size(40.dp),
                bitmap = imageBitmap,
                contentDescription = "유저 아이콘"
            )
        } else {
            Image(
                imageVector = Icons.Filled.Face,
                contentDescription = "유저 아이콘 없음"
            )
        }
    }
}
