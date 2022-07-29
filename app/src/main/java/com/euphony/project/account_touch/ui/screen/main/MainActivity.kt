package com.euphony.project.account_touch.ui.screen.main

import android.os.Bundle
import android.widget.Toast
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
                    accountViewModel,
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
    userViewModel: UserViewModel,
    bankViewModel: BankViewModel,
    receivedViewModel: ReceivedViewModel,
    accountViewModel: AccountViewModel,
    onAddAccountInValid: () -> Unit,
    onModifyAccountInValid: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Accounts.route
    ) {
        composable(route = Accounts.route) {
            MainBottomSheetScreen(
                accountViewModel,
                bankViewModel,
                onReceivedIconClick = {
                    navController.navigateSingleTopTo(ReceivedAccounts.route)
                },
                onAccountClick = {
                    navController.navigateSingleTopTo(TransmitAccount.route)
                },
                onAddAccountInValid = { onAddAccountInValid() },
                onModifyAccountInValid = { onModifyAccountInValid() }
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

