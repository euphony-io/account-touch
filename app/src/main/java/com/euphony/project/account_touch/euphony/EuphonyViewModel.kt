package com.euphony.project.account_touch.euphony

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.euphony.project.account_touch.euphony.dto.InfoModel
import com.euphony.project.account_touch.utils.FormatUtil
import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager

class EuphonyViewModel : ViewModel() {

    private val _info = MutableLiveData<InfoModel>()
    val info get() = _info

    private val txManager: EuTxManager by lazy {
        EuTxManager()
    }
    private val rxManager : EuRxManager by lazy {
        EuRxManager()
    }

    fun speak(bankId: Int, accountNumber: String){
        txManager.euInitTransmit(FormatUtil.infoToJson(
            InfoModel(bankId, accountNumber)
        ))

        txManager.process(-1)
    }

    fun listen() {
        rxManager.listen()

        rxManager.acousticSensor = AcousticSensor {
            _info.value = FormatUtil.jsonToInfo(it)
        }

    }

    fun destory(){
        rxManager.finish()
        txManager.stop()
    }
}
