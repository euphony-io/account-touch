package com.euphony.project.account_touch.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.ui.theme.Black_333B58
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.Gray_F4F4F4
import com.euphony.project.account_touch.utils.AssetsUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChooseBankContent(
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
) {
    val bankBitmaps = AssetsUtil.getBitmaps(LocalContext.current, "banks")
    BottomSheetTitle(modalBottomSheetState, coroutineScope, "은행 선택")
    BanksContent(bankBitmaps)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetTitle(
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    title: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = title,
            fontSize = 16.sp,
            color = Blue_6D95FF,
            fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "닫기",
                tint = Blue_6D95FF
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BanksContent(bankBitmaps: List<ImageBitmap?>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(12) { // Dummy Data
            BankItem(bankBitmaps[0])
        }
    }
}

@Composable
fun BankItem(imageBitmap: ImageBitmap?) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Gray_F4F4F4
    ) {
        Column(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageBitmap == null) {
                Image(Icons.Filled.HideImage, contentDescription = "이미지 없음")
            } else {
                Image(bitmap = imageBitmap, contentDescription = "은행 로고")
            }
            Text(
                text = "부산은행",
                color = Black_333B58,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetTitlePreview() {
    BottomSheetTitle(
        modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
        coroutineScope = rememberCoroutineScope(),
        title = "은행 선택"
    )
}

@Preview(showBackground = true)
@Composable
fun BankItemPreview() {
    val imageBitmap = AssetsUtil.getBitmap(LocalContext.current, "banks/bnk_bank.png")
    BankItem(imageBitmap = imageBitmap)
}
