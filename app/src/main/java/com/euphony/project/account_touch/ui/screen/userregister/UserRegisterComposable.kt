package com.euphony.project.account_touch.ui.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.euphony.project.account_touch.R
import com.euphony.project.account_touch.ui.screen.UserRegisterActivity.Companion.navController
import com.euphony.project.account_touch.ui.screen.UserRegisterActivity.Companion.scope
import com.euphony.project.account_touch.ui.screen.UserRegisterActivity.Companion.state
import com.euphony.project.account_touch.ui.theme.grey
import com.euphony.project.account_touch.ui.theme.mainColor
import com.euphony.project.account_touch.ui.theme.white
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserRegisterActivity : ComponentActivity() {

    companion object{
        @OptIn(ExperimentalMaterialApi::class)
        lateinit var state:BottomSheetScaffoldState
        lateinit var scope: CoroutineScope
        lateinit var navController:NavHostController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            Navigation(navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "register"   //처음 시작
    ){
        //메인 화면
        composable("main"){
            LoadingMainView()
        }

        //유저 등록 화면
        composable("register"){
            state = rememberBottomSheetScaffoldState(
                bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
            )
            scope = rememberCoroutineScope()
            BottomSheetScaffold(
                scaffoldState = state,
                sheetContent = {
                    BottomSheetContent()
                },
                sheetPeekHeight = 0.dp,
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            ){
                loadingUserRegisterView()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun loadingUserRegisterView(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 100.dp, bottom = 50.dp)
    ) {
        loadText(str = stringResource(R.string.user_register_str))
        space(50)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        if (state.bottomSheetState.isCollapsed)
                            state.bottomSheetState.expand()
                        else
                            state.bottomSheetState.collapse()
                    }
                },
            contentAlignment = Alignment.Center){
            ProfileImage(
                profile = painterResource(id = R.drawable.ic_profile_smile),
                width = 120, height = 120, color = mainColor
            )
        }
        space(100)
        NicknameEditText()
        space(value = 30)
        NextButton()
    }
}

@Composable
fun loadText(str:String) {
    Text(
        str,
        color = mainColor,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

//프로필 bg+icon 중첩 이미지 뷰
@Composable
fun ProfileImage(
    profile: Painter,
    width: Int, height:Int,
    color: Color,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .width(width.dp)
            .height(height.dp)
            .padding(start = 20.dp, top = 20.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = color,
        elevation = 5.dp,
    ){
        Box(
            modifier = modifier.height(200.dp),
            contentAlignment = Alignment.Center
        ){
            Image(painter = profile, contentDescription = "", alignment = Alignment.Center)
        }
    }
}

@Composable
fun NicknameEditText(){
    var text by remember { mutableStateOf("")}

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(8.dp),
        value = text,
        onValueChange = {text = it},
        singleLine = true,
        label = { Text("닉네임", color = mainColor, fontSize = 12.sp) })
}

@Composable
fun NextButton(){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = mainColor, contentColor = white
        ),
        onClick = {
            //사용자 프로필 설정 완료
            //메인액티비티 전환
            navController.navigate("main")
    }){
        Text(text = "다음")
    }
}

@Composable
fun space(value:Int){
    Spacer(modifier = Modifier.height(value.dp))
}

/* 아래부터 BottomSheet 관련 코드 */
@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview(){
    BottomSheetContent()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(){
    Column {
        Row(Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)){
            Text(
                "이모지 설정",
                color = mainColor,
                textAlign = TextAlign.Left,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                Image(
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                        .clickable(onClick = {
                            scope.launch {
                                state.bottomSheetState.collapse()
                            }
                        }),
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "닫기",
                )
            }
        }
        space(20)

        //유저 현재 이모지
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            ProfileImage(
                profile = painterResource(id = R.drawable.ic_profile_smile),
                width = 120, height = 120, color = mainColor
            )
        }
        space(20)
        
        //이모지 리스트
        BottomSheetListItem()
        space(20)
        
        //확인 버튼
        Button(
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
                .align(Alignment.CenterHorizontally)
                .clickable(onClick = {
                    scope.launch {
                        state.bottomSheetState.collapse()
                    }
                }),
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = mainColor, contentColor = white
            ),
            onClick = {
                //프로필 변경 - DB작업
                scope.launch {
                    state.bottomSheetState.collapse()
                }
            }){
            Text(text = "확인")
        }
        
        space(value = 20)
    }
}

@Composable
fun BottomSheetListItem(){
    val context = LocalContext.current

    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_party,
                onItemClick = {icon ->
                    Toast.makeText(
                        context, "파티 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_sad,
                onItemClick = {icon ->
                    Toast.makeText(
                        context, "슬픔 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_smile,
                onItemClick = {icon ->
                    Toast.makeText(
                        context, "미소 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_sunglasses,
                onItemClick = {icon ->
                    Toast.makeText(
                        context, "선글라스 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_pow,
                onItemClick = {icon ->
                    Toast.makeText(
                        context, "응가 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_twinkle,
                onItemClick = {icon ->
                    Toast.makeText(
                        context, "별 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_ghost,
                onItemClick = {icon ->
                    Toast.makeText(
                        context, "유령 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
            BottomSheetImage(
                Imoticon = R.drawable.ic_profile_laugh,
                onItemClick = {icon ->
                    Toast.makeText(
                        context, "웃는 이모지 선택", Toast.LENGTH_SHORT
                    ).show()
                })
        }
    }
}

@Composable
fun BottomSheetImage(
    Imoticon: Int,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    var width = 100
    var height = 100
    Card(
        modifier = modifier
            .width(width.dp)
            .height(height.dp)
            .padding(start = 20.dp, top = 20.dp)
            .clickable(onClick = { onItemClick(Imoticon) }),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = grey,
        elevation = 5.dp,
    ){
        Box(
            modifier = modifier.height(200.dp),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(id = Imoticon), contentDescription = "", alignment = Alignment.Center)
        }
    }
}
