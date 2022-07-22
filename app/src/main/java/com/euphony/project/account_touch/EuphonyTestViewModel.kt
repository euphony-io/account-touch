package com.euphony.project.account_touch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        } else { // true
            _isSpeaking.value = false
        }
    }

    fun listen(euRxManager: EuRxManager) {
        if (isListening.value == false) {
            _isListening.value = true
        } else { // true
            _isListening.value = false
        }
    }
}
