package com.euphony.project.account_touch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.euphony.project.account_touch.ui.component.ChooseBankScreen
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme
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
            ChooseBankScreen(
                onCloseClick = {
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                    }
                }
            )
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
