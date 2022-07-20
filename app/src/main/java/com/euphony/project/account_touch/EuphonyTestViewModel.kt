package com.euphony.project.account_touch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager

class EuphonyTestViewModel: ViewModel() {
    // TODO: will be used as Singleton
    private val euTxManager = EuTxManager()
    private val euRxManager = EuRxManager()

    private val _isSpeaking: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSpeaking: LiveData<Boolean> get() = _isSpeaking

    private val _isListening: MutableLiveData<Boolean> = MutableLiveData(false)
    val isListening: LiveData<Boolean> get() = _isListening

    fun speak() {
        if (isSpeaking.value == false) {
            _isSpeaking.value = true
        } else { // true
            _isSpeaking.value = false
        }
    }

    fun listen() {
        if (isListening.value == false) {
            _isListening.value = true
        } else { // true
            _isListening.value = false
        }
    }
}
