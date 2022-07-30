package com.euphony.project.account_touch.ui.screen.main

interface Destination {
    val route: String
}

object Accounts : Destination {
    override val route: String = "accounts"
}

object TransmitAccount : Destination {
    override val route: String = "transmit"
}

object ReceivedAccounts : Destination {
    override val route: String = "received"
}

object ReceivedAccountDetail : Destination {
    override val route: String = "received-detail"
}

val mainScreens = listOf(Accounts, TransmitAccount, ReceivedAccounts, ReceivedAccountDetail)
