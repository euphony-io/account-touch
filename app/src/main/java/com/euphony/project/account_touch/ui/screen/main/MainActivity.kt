package com.euphony.project.account_touch.ui.screen.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.getSystemService
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.euphony.project.account_touch.data.global.AccountWithBank
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.euphony.EuphonyViewModel
import com.euphony.project.account_touch.ui.screen.main.receivedaccounts.ReceivedAccountsScreen
import com.euphony.project.account_touch.ui.screen.main.transmitaccount.TransmitAccountScreen
import com.euphony.project.account_touch.ui.screen.receivedetail.ReceivedDetailScreen
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme
import java.lang.Exception
import com.euphony.project.account_touch.ui.viewmodel.AccountViewModel
import com.euphony.project.account_touch.ui.viewmodel.BankViewModel
import com.euphony.project.account_touch.ui.viewmodel.ReceivedViewModel
import com.euphony.project.account_touch.ui.viewmodel.UserViewModel
import com.euphony.project.account_touch.utils.model.UserIcon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.callbackFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel by viewModels<UserViewModel>()
    private val bankViewModel by viewModels<BankViewModel>()
    private val receivedViewModel by viewModels<ReceivedViewModel>()
    private val accountViewModel by viewModels<AccountViewModel>()
    private val euphonyViewModel by viewModels<EuphonyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        euphonyViewModel.listen()
        setContent {

            AccounttouchTheme {
                val user = userViewModel.user.observeAsState().value ?: User(nickname = "temp", icon = UserIcon.GHOST)
                val accounts = accountViewModel.accounts.observeAsState().value ?: listOf()

                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                val currentScreen =
                    mainScreens.find { it.route == currentDestination?.route } ?: Accounts

                MainNavHost(
                    navController,
                    user,
                    accounts,
                    bankViewModel,
                    receivedViewModel,
                    accountViewModel,
                    euphonyViewModel,
                    onAddAccountInValid = {
                        Toast.makeText(this, "계좌 이름을 10자 이내로 작성해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onModifyAccountInValid = {
                        Toast.makeText(this, "계좌 수정에 실패하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
    }

}

@Composable
fun MainNavHost(
    navController: NavHostController,
    user: User,
    accounts: List<AccountWithBank>,
    bankViewModel: BankViewModel,
    receivedViewModel: ReceivedViewModel,
    accountViewModel: AccountViewModel,
    euphonyViewModel: EuphonyViewModel,
    onAddAccountInValid: () -> Unit,
    onModifyAccountInValid: () -> Unit,
) {
    var accountIndex by remember { mutableStateOf(-1) }

    NavHost(
        navController = navController,
        startDestination = Accounts.route
    ) {
        composable(route = Accounts.route) {
            MainBottomSheetScreen(
                user,
                accounts,
                accountViewModel,
                bankViewModel,
                onReceivedIconClick = {
                    navController.navigateSingleTopTo(ReceivedAccounts.route)
                },
                onAccountClick = {
                    accountIndex = it
                    navController.navigateSingleTopTo(TransmitAccount.route)
                    val account = accounts[accountIndex].account;
                    euphonyViewModel.speak(
                        account.bank_id,
                        account.accountNumber
                    )
                },
                onAddAccountInValid = { onAddAccountInValid() },
                onModifyAccountInValid = { onModifyAccountInValid() }
            )
        }
        composable(route = TransmitAccount.route) {
            TransmitAccountScreen(
                accounts[accountIndex],
                user,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = ReceivedAccounts.route) {
            ReceivedAccountsScreen(
                user,
                receivedViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

//클립보드 복사하기
@Composable
fun copyStr(str: String){
    var context = LocalContext.current
    var clipboardManager = LocalContext.current.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("account", str)
    clipboardManager.setPrimaryClip(clipData)

    //복사 완료 토스트
    Toast.makeText(context, "복사 완료", Toast.LENGTH_SHORT).show()
}

//설치된 앱 리스트 얻어오기
@Composable
fun getPackageList(){
    val bankApp = "com.shinhan.sbanking"    //temp data

    val pkgMgr = LocalContext.current.packageManager
    val it = Intent(Intent.ACTION_MAIN, null)
    it.addCategory(Intent.CATEGORY_LAUNCHER)
    val apps = pkgMgr.queryIntentActivities(it, 0)

    var set = mutableSetOf<String>()
    for(app in apps) {
        Log.d("tag", app.activityInfo.packageName)
        set.add(app.activityInfo.packageName)
    }

    val context = LocalContext.current
    try{
        context.packageManager.getLaunchIntentForPackage("com.shinhan.sbanking")?.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }?.let { intent ->
            startActivity(context, intent, null)
        }
    }
    catch(e: Exception){
        Toast.makeText(context, "은행 어플이 존재하지 않습니다", Toast.LENGTH_SHORT).show()
        /*
        val marketLaunch: Intent = Intent(Intent.ACTION_VIEW)
        marketLaunch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        marketLaunch.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + bankApp))
        startActivity(marketLaunch)*/
    }
}
