package com.euphony.project.account_touch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager

class EuphonyTestViewModel: ViewModel() {

    private val _isSpeaking: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSpeaking: LiveData<Boolean> get() = _isSpeaking

    private val _isListening: MutableLiveData<Boolean> = MutableLiveData(false)
    val isListening: LiveData<Boolean> get() = _isListening

    fun speak(euTxManager: EuTxManager) {
        if (isSpeaking.value == false) {
            _isSpeaking.value = true
            euTxManager.euInitTransmit("1234")
            euTxManager.process(-1)
        } else { // true
            _isSpeaking.value = false
            euTxManager.stop()
        }
    }

    fun listen(euTxManager: EuTxManager, euRxManager: EuRxManager) {
        if (isListening.value == false) {
            _isListening.value = true
            euRxManager.listen()
            euRxManager.acousticSensor = AcousticSensor {
                Log.i("EuphonyTestViewModel", it)
                _isSpeaking.value = false
                _isListening.value = false
                euTxManager.stop()
            }
        } else { // true
            _isListening.value = false
            euRxManager.finish()
        }
    }
}
