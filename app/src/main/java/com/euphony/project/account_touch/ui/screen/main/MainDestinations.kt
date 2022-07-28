package com.euphony.project.account_touch.ui.screen.main

interface MainDestination {
    val route: String
}

object Accounts : MainDestination {
    override val route: String = "accounts"
}

object TransmitAccount : MainDestination {
    override val route: String = "transmit account"
}

object ReceivedAccounts : MainDestination {
    override val route: String = "received accounts"
}

val mainScreens = listOf(Accounts, TransmitAccount, ReceivedAccounts)
