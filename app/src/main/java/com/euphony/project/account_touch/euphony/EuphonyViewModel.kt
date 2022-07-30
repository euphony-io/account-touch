package com.euphony.project.account_touch.euphony

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import co.euphony.rx.AcousticSensor
import co.euphony.rx.EuRxManager
import co.euphony.tx.EuTxManager
import co.euphony.util.EuOption
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.euphony.dto.AccountInfoModel
import com.euphony.project.account_touch.euphony.dto.UserInfoModel
import com.euphony.project.account_touch.ui.viewmodel.AccountViewModel
import com.euphony.project.account_touch.utils.FormatUtil

class EuphonyViewModel (application: Application) : AndroidViewModel(application) {

    private val _isSpeaking = MutableLiveData(false)
    val isSpeaking get() = _isSpeaking

    private val _isListening = MutableLiveData(false)
    val isListening get() = _isListening

    private val _isProcessing = MutableLiveData(false)
    val isProcessing get() = _isProcessing

    private val _accountInfo = MutableLiveData<AccountInfoModel>()
    val accountInfo get() = _accountInfo

    private val _usersInfo: MutableLiveData<List<UserInfoModel>> = MutableLiveData(listOf())
    val result get() = _usersInfo

    private val txManager: EuTxManager by lazy {
        val context = getApplication<Application>().applicationContext
        EuTxManager(context)
    }
    private val rxManager : EuRxManager by lazy {
        EuRxManager(EuOption.ModeType.EUPI)
    }

    // 항상 모두에게 자신의 정보 보냄 (21000)
    fun speakUser(phone: String, sendObj: User) {
        txManager.code = FormatUtil.userInfoToJson(
            UserInfoModel(phone, sendObj.nickname, sendObj.icon)
        )
        txManager.callEuPI(25000.0, EuTxManager.EuPIDuration.LENGTH_FOREVER)
    }

    // 항상 모두에게 보냄 (25000)
    fun speakAll(sendObj: Received) {
        txManager.code = FormatUtil.accountInfoToJson(
            AccountInfoModel(
                sendObj.accountNumber,
                sendObj.accountNickname,
                sendObj.speakerNickName,
                sendObj.speakerIcon,
                sendObj.bank_id
            )
        )
        txManager.callEuPI(25000.0, EuTxManager.EuPIDuration.LENGTH_FOREVER)
    }

    // 특정인에게 보냄 (24000)
    fun speak(key: String, sendObj: Received){
        txManager.code = FormatUtil.accountInfoToJson(
            key,
            AccountInfoModel(
                sendObj.accountNumber,
                sendObj.accountNickname,
                sendObj.speakerNickName,
                sendObj.speakerIcon,
                sendObj.bank_id
            )
        )

        txManager.callEuPI(24000.0, EuTxManager.EuPIDuration.LENGTH_LONG)
    }

    // 유저 정보 받음 (모두에게) (21000)
    fun listenUser(){
        if (isListening.value == false) {
            rxManager.listen()
        }

        rxManager.setOnWaveKeyDown(21000){ }
        //rxManager.setFrequencyForDetect(21000)

        rxManager.acousticSensor = AcousticSensor {
            val obj = FormatUtil.jsonToUserInfo(it)
            _usersInfo.value = _usersInfo.value?.plus(obj) ?: listOf(obj)
        }
    }

    // 계좌 받음 (모두에게: 25000, 특정인에게: 24000)
    fun listen(key: String) {
        if (isListening.value == false) {
            rxManager.listen()
        }

        rxManager.setOnWaveKeyPressed(25000){ }
        rxManager.setOnWaveKeyPressed(24000){ }

        rxManager.acousticSensor = AcousticSensor {
            _isListening.postValue(false)
            _isProcessing.postValue(false)
            if(it.startsWith("@")){
                _accountInfo.postValue(FormatUtil.jsonToAccountInfo(key, it))
                //_accountInfo 값 ""이면 복호화 실패
            } else {
                _accountInfo.postValue(FormatUtil.jsonToAccountInfo(it))
            }
        }

        _isProcessing.postValue(true)
        _isListening.postValue(true)
    }

    fun destory(){
        rxManager.finish()
        txManager.stop()
    }
}
