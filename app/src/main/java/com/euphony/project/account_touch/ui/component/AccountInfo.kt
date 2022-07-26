package com.euphony.project.account_touch.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Switch
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.euphony.project.account_touch.ui.theme.Gray_ECEDED
import com.euphony.project.account_touch.ui.theme.Gray_F4F4F4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountInfo(
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    isEditClicked: Boolean,
    onEditClick: () -> Unit,
) {
    AccountInfoTitle(
        modalBottomSheetState,
        coroutineScope,
        isEditClicked,
        onEditClick = onEditClick
    )
    Account()
    AccountColors()
    AccountOptions()
    AccountButton()
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
fun AccountColors() {
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
                AccountColorItem(it, colors[it], size, clicked) {
                    clicked = it
                }
            }
        }
    }
}

@Composable
fun AccountColorItem(
    index: Int,
    color: Color,
    size: IntSize,
    clicked: Int,
    onClick: (Int) -> Unit,
) {
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
            .background(color = color)
            .clickable { onClick(index) },
        contentAlignment = Alignment.Center
    ) {
        if (clicked == index) {
            Icon(Icons.Filled.Check, contentDescription = "체크")
        }
    }
}

@Composable
fun AccountOptions() {
    var isShare by remember { mutableStateOf(false) }
    var isEncrypt by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.onSizeChanged { size = it }
        ) {
            Text(
                text = "항상 공유",
                fontSize = 12.sp,
                color = Blue_6D95FF,
                fontWeight = FontWeight.Bold
            )
            Switch(
                checked = isShare,
                onCheckedChange = { isShare = !isShare }
            )
        }

        Divider(
            color = Gray_ECEDED,
            modifier = Modifier.then(
                with(LocalDensity.current) {
                    Modifier.size(
                        width = 1.dp,
                        height = size.height.toDp()
                    )
                }
            )
        )

        Column {
            Text(
                text = "암호 설정",
                fontSize = 12.sp,
                color = Blue_6D95FF,
                fontWeight = FontWeight.Bold
            )
            Switch(
                checked = isEncrypt,
                onCheckedChange = { isEncrypt = !isEncrypt }
            )
        }
    }
}

@Composable
fun AccountButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(0.3f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Blue_6D95FF
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "확인",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun AccountInfoTitlePreview() {
    AccountInfoTitle(
        modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
        coroutineScope = rememberCoroutineScope(),
        isEditClicked = false,
        onEditClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun AccountPreview() {
    Account()
}

@Preview(showBackground = true)
@Composable
fun AccountColorItemPreview() {
    AccountColorItem(0, Color1, IntSize(500, 1000), 0) {}

}

@Preview(showBackground = true)
@Composable
fun AccountOptionsPreview() {
    AccountOptions()
}

@Preview(showBackground = true)
@Composable
fun AccountButtonPreview() {
    AccountButton()
}
