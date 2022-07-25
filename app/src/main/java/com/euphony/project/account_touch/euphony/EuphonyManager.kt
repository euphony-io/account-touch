package com.euphony.project.account_touch.euphony

import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager

object EuphonyManager {
    private val euTxManager = EuTxManager()
    private val euRxManager = EuRxManager()

    fun getEuTxManager() = euTxManager
    fun getEuRxManager() = euRxManager

}
