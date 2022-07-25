package com.euphony.project.account_touch.euphony

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager

class EuphonyViewModel: ViewModel() {

    private val _isSpeaking: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSpeaking: LiveData<Boolean> get() = _isSpeaking

    private val _isListening: MutableLiveData<Boolean> = MutableLiveData(false)
    val isListening: LiveData<Boolean> get() = _isListening

    private val data = "{\"account\": { \"nickname\" : \"test\", \"accountNumber\" : \"123123123123\", }, \"bank\" : { \"id\" : \"1\" }, \"user\" : { \"nickname\" : \"test\", \"icon\" : \"path\" } }"

    fun speak(euTxManager: EuTxManager) {
        if (isSpeaking.value == false) {
            _isSpeaking.value = true
            euTxManager.euInitTransmit(data)
            euTxManager.process(-1)
        } else { // true
            _isSpeaking.value = false
            euTxManager.stop()
        }
    }

    fun listen(euRxManager: EuRxManager) {
        if (isListening.value == false) {
            _isListening.value = true
            euRxManager.listen()
            euRxManager.acousticSensor = AcousticSensor {
                Log.i("EuphonyTestViewModel", it)
                _isListening.value = false
            }
        } else { // true
            _isListening.value = false
            euRxManager.finish()
        }
    }
}
