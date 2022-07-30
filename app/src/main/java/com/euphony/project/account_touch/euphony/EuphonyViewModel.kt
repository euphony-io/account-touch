package com.euphony.project.account_touch.euphony

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.euphony.project.account_touch.data.account.repository.AccountRepository
import com.euphony.project.account_touch.data.received.dto.CreateReceivedRequest
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.data.received.repository.ReceivedRepository
import com.euphony.project.account_touch.euphony.dto.InfoModel
import com.euphony.project.account_touch.utils.FormatUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EuphonyViewModel @Inject constructor (
    private val repository: ReceivedRepository
) : ViewModel() {

    private val _info = MutableLiveData<InfoModel>()
    val info get() = _info

    private val txManager: EuTxManager by lazy {
        EuTxManager()
    }
    private val rxManager : EuRxManager by lazy {
        EuRxManager()
    }

    fun speak(bankId: Long, accountNumber: String, onShowDialog: () -> Unit){
        txManager.euInitTransmit(FormatUtil.infoToJson(
            InfoModel(bankId, accountNumber)
        ))

        txManager.process(-1)
        viewModelScope.launch {
            delay(5000)
            txManager.stop()
            onShowDialog()
        }
    }

    fun listen(onNavigate: (Long) -> Unit) {
        rxManager.listen()

        rxManager.acousticSensor = AcousticSensor {
            val received = FormatUtil.jsonToInfo(it)

            _info.value = received
            save(CreateReceivedRequest(received.id, received.num))
            val id = repository.getReceivedList().asLiveData().value?.get(0)?.id ?: 0
            onNavigate(id)
        }
    }

    private fun save(received: CreateReceivedRequest)  = viewModelScope.launch {
        repository.createAt(received)
    }

    fun destory(){
        rxManager.finish()
        txManager.stop()
    }
}
