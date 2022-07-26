package com.euphony.project.account_touch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme
import com.euphony.project.account_touch.ui.theme.Black_333B58
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.Color1
import com.euphony.project.account_touch.ui.theme.Color10
import com.euphony.project.account_touch.ui.theme.Color2
import com.euphony.project.account_touch.ui.theme.Color3
import com.euphony.project.account_touch.ui.theme.Color4
import com.euphony.project.account_touch.ui.theme.Color5
import com.euphony.project.account_touch.ui.theme.Color6
import com.euphony.project.account_touch.ui.theme.Color7
import com.euphony.project.account_touch.ui.theme.Color8
import com.euphony.project.account_touch.ui.theme.Color9
import com.euphony.project.account_touch.ui.theme.Gray_F4F4F4
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
    var isEditClicked by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(

        sheetContent = {
            AccountInfoTitle(modalBottomSheetState, coroutineScope, isEditClicked) {
                isEditClicked = !isEditClicked
            }
            Account()
            AccountColor()
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
                isEditClicked = false
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
fun AccountInfoTitle(
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    isEditClicked: Boolean,
    onEditClick: () -> Unit,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("부산은행")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isEditClicked) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = textFieldValue.text,
                    fontSize = 16.sp,
                    color = Blue_6D95FF,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { onEditClick() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "수정",
                        tint = Blue_6D95FF
                    )
                }
            }
        } else {
            TextField(
                modifier = Modifier.padding(start = 16.dp),
                shape = RoundedCornerShape(24.dp),
                value = textFieldValue,
                onValueChange = {
                    if (it.text.length <= 10) textFieldValue = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                maxLines = 1,
            )
        }
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

@Composable
fun Account() {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 32.dp)
    ) {
        Text(
            text = "계좌번호",
            fontSize = 12.sp,
            color = Blue_6D95FF,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            label = {
                Text(text = "계좌번호를 입력해주세요.", color = Black_333B58)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Gray_F4F4F4,
                focusedIndicatorColor = Blue_6D95FF
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountColor() {
    val colors =
        listOf(Color1, Color2, Color3, Color4, Color5, Color6, Color7, Color8, Color9, Color10)
    var size by remember { mutableStateOf(IntSize.Zero) }
    var clicked by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 4.dp)
            .onSizeChanged { size = it },
    ) {
        Text(
            text = "색상",
            fontSize = 12.sp,
            color = Blue_6D95FF,
            fontWeight = FontWeight.Bold
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(5),
            modifier = Modifier
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(count = colors.size) {
                Box(
                    modifier = Modifier
                        .then(
                            with(LocalDensity.current) {
                                Modifier.size(
                                    width = size.width.toDp() / 5,
                                    height = (size.width.toDp() - 96.dp) / 5 // horizontalArrangement * 4
                                )
                            }
                        )
                        .clip(shape = CircleShape)
                        .background(color = colors[it])
                        .clickable {
                            clicked = it
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (clicked == it) {
                        Icon(Icons.Filled.Check, contentDescription = "체크")
                    }
                }
            }
        }
    }
}
