package com.euphony.project.account_touch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccounttouchTheme {
                ModalBottomSheet()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheet() {
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            ChooseBankContent(modalBottomSheetState, coroutineScope)
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    modalBottomSheetState.show()
                }
            }) {
                Text(text = "OPEN BOTTOM SHEET")
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChooseBankContent(
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
) {
    BottomSheetTitle(modalBottomSheetState, coroutineScope, "은행 선택")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetTitle(
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    title: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = title,
            fontSize = 18.sp,
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
