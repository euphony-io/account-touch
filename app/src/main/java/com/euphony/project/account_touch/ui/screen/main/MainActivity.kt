package com.euphony.project.account_touch.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.euphony.project.account_touch.ui.viewmodel.AccountViewModel
import com.euphony.project.account_touch.ui.viewmodel.BankViewModel
import com.euphony.project.account_touch.ui.viewmodel.ReceivedViewModel
import com.euphony.project.account_touch.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel by viewModels<UserViewModel>()
    private val bankViewModel by viewModels<BankViewModel>()
    private val receivedViewModel by viewModels<ReceivedViewModel>()
    private val accountViewModel by viewModels<AccountViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccounttouchTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                val currentScreen =
                    mainScreens.find { it.route == currentDestination?.route } ?: Accounts

                MainNavHost(
                    navController,
                    userViewModel,
                    bankViewModel,
                    receivedViewModel,
                    accountViewModel
                )
            }
        }
    }

}

@Composable
fun MainNavHost(
    navController: NavHostController,
    userViewModel: UserViewModel,
    bankViewModel: BankViewModel,
    receivedViewModel: ReceivedViewModel,
    accountViewModel: AccountViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Accounts.route
    ) {
        composable(route = Accounts.route) {
            MainBottomSheetScreen(
                accountViewModel,
                onReceivedIconClick = {
                    navController.navigateSingleTopTo(ReceivedAccounts.route)
                }
            ) {
                navController.navigateSingleTopTo(TransmitAccount.route)
            }
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

