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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.getSystemService
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.euphony.project.account_touch.ui.screen.main.receivedaccounts.ReceivedAccountsScreen
import com.euphony.project.account_touch.ui.screen.main.transmitaccount.TransmitAccountScreen
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccounttouchTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                val currentScreen =
                    mainScreens.find { it.route == currentDestination?.route } ?: Accounts

                MainNavHost(navController)
            }
        }
    }
}

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Accounts.route
    ) {
        composable(route = Accounts.route) {
            MainBottomSheetScreen(
                onReceivedIconClick = {
                    navController.navigateSingleTopTo(ReceivedAccounts.route)
                },
                onAccountClick = {
                    navController.navigateSingleTopTo(TransmitAccount.route)
                }
            )
        }
        composable(route = TransmitAccount.route) {
            TransmitAccountScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = ReceivedAccounts.route) {
            ReceivedAccountsScreen(
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