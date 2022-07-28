package com.euphony.project.account_touch.ui.screen.main

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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.ui.theme.Black_333B58
import com.euphony.project.account_touch.ui.theme.Blue_6D95FF
import com.euphony.project.account_touch.ui.theme.Gray_F4F4F4
import com.euphony.project.account_touch.utils.model.Color

@Composable
fun AccountInfoScreen(
    isEditClicked: Boolean,
    onCloseClick: () -> Unit,
    onEditClick: () -> Unit,
    isAddContent: Boolean
) { // TODO: viewModel (insert NEW ACCOUNT)
    AccountInfo(isEditClicked, onCloseClick, onEditClick, isAddContent)
}

@Composable
fun AccountInfo(
    isEditClicked: Boolean,
    onCloseClick: () -> Unit,
    onEditClick: () -> Unit,
    isEditContent: Boolean
) {
    var accountNickname by remember { mutableStateOf(TextFieldValue("부산은행")) }
    var accountNumber by remember { mutableStateOf(TextFieldValue("")) }
    var colorIndex by remember { mutableStateOf(-1) }
    val colors = Color.values()
    var isShare by remember { mutableStateOf(false) }

    Column {
        AccountInfoTitle(
            isEditClicked,
            onCloseClick = onCloseClick,
            onEditClick = onEditClick,
            textFieldValue = accountNickname,
            onValueChange = {
                if (it.text.length <= 10) accountNickname = it
            }
        )
        Account(
            textFieldValue = accountNumber,
            onValueChange = {
                accountNumber = it
            },
            isEditContent = isEditContent
        )
        AccountColors(
            colors = colors,
            colorIndex = colorIndex,
            onColorClick = {
                colorIndex = it
            }
        )
        AccountOption(
            isShare = isShare,
            onCheckedChange = {
                isShare = !isShare
            }
        )
        AccountButton() // TODO: NEW ACCOUNT 생성
    }
}

@Composable
fun AccountInfoTitle(
    isEditClicked: Boolean,
    onCloseClick: () -> Unit,
    onEditClick: () -> Unit,
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
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
                onValueChange = { onValueChange(it) },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = colorResource(id = R.color.transparent),
                    unfocusedIndicatorColor = colorResource(id = R.color.transparent)
                ),
                maxLines = 1,
            )
        }
        IconButton(
            onClick = { onCloseClick() }
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
fun Account(
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    isEditContent: Boolean
) {

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
            enabled = isEditContent,
            onValueChange = { onValueChange(it) },
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
fun AccountColors(colors: Array<Color>, colorIndex: Int, onColorClick: (Int) -> Unit) {
    var size by remember { mutableStateOf(IntSize.Zero) }

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
                AccountColorItem(
                    index = it,
                    color = colors[it],
                    size = size,
                    colorIndex = colorIndex,
                    onColorClick = onColorClick
                )
            }
        }
    }
}

@Composable
fun AccountColorItem(
    index: Int,
    color: Color,
    size: IntSize,
    colorIndex: Int,
    onColorClick: (Int) -> Unit,
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
            .background(color = colorResource(id = color.colorId))
            .clickable { onColorClick(index) },
        contentAlignment = Alignment.Center
    ) {
        if (colorIndex == index) {
            Icon(Icons.Filled.Check, contentDescription = "체크")
        }
    }
}

@Composable
fun AccountOption(isShare: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "항상 공유",
                fontSize = 12.sp,
                color = Blue_6D95FF,
                fontWeight = FontWeight.Bold
            )
            Switch(
                modifier = Modifier
                    .padding(start = 36.dp),
                checked = isShare,
                onCheckedChange = { onCheckedChange(it) }
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
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountInfoPreview() {
    AccountInfo(
        isEditClicked = false,
        onCloseClick = {},
        onEditClick = {},
        isEditContent = true
    )
}
