package com.euphony.project.account_touch.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.euphony.project.account_touch.ui.screen.main.receivedaccounts.ReceivedAccountsScreen
import com.euphony.project.account_touch.ui.screen.main.transmitaccount.TransmitAccountScreen
import com.euphony.project.account_touch.ui.theme.AccounttouchTheme

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

// navigate 중복 호출 방지 (google codelab)
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

