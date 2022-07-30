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
import com.euphony.project.account_touch.euphony.dto.InfoModel
import com.euphony.project.account_touch.euphony.dto.UserInfoModel
import com.euphony.project.account_touch.utils.FormatUtil

class EuphonyViewModel (application: Application) : AndroidViewModel(application) {

    private val _isSpeaking = MutableLiveData(false)
    val isSpeaking get() = _isSpeaking

    private val _isListening = MutableLiveData(false)
    val isListening get() = _isListening

    private val _isProcessing = MutableLiveData(false)
    val isProcessing get() = _isProcessing


    private val _accountEveryInfo = MutableLiveData<Received>()
    val accountEveryInfo get() = _accountEveryInfo

    private val _accountInfo = MutableLiveData<Received>()
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

    // 항상 모두에게 보냄 (21000)
    fun speakAll(key: String, accountObj: Account, userObj: User) {
        txManager.code = FormatUtil.infoToJson(
            InfoModel(
                UserInfoModel(key, userObj.nickname, userObj.icon),
                AccountInfoModel(
                    accountObj.accountNumber,
                    accountObj.nickname,
                    userObj.nickname,
                    userObj.icon,
                    accountObj.bank_id
                )
            )
        )

        txManager.callEuPI(21000.0, EuTxManager.EuPIDuration.LENGTH_FOREVER)
    }

    // 특정인에게 보냄 (24000)
    fun speak(key: String, accountObj: Account, userObj: User){
        txManager.stop()
        txManager.code = FormatUtil.accountInfoToJson(
            key,
            AccountInfoModel(
                accountObj.accountNumber,
                accountObj.nickname,
                userObj.nickname,
                userObj.icon,
                accountObj.bank_id
            )
        )
        txManager.callEuPI(24000.0, EuTxManager.EuPIDuration.LENGTH_LONG)
    }

    // 유저 정보, 계좌 받음 (모두에게) (21000)
    fun listenAll(){
        if (isListening.value == false) {
            rxManager.listen()
        }

        rxManager.setOnWaveKeyDown(21000){ }

        rxManager.acousticSensor = AcousticSensor {
            val infoInfo = FormatUtil.jsonToInfo(it)

            val userInfo = infoInfo.userInfo;
            val accountInfo = infoInfo.accountInfo;

            // 주변인 리스트
            _usersInfo.value = _usersInfo.value?.plus(userInfo) ?: listOf(userInfo)

            //항상 공유하는 계좌 받음
            if(accountInfo != null){
                _accountEveryInfo.value = Received(
                    0,
                    accountInfo.bank_id,
                    accountInfo.accountNickname,
                    accountInfo.accountNumber,
                    userInfo.nickname,
                    userInfo.icon,
                )
            }
        }
    }

    // 계좌 받음 (특정인에게: 24000)
    fun listen(key: String) {
        if (isListening.value == false) {
            rxManager.listen()
        }

        rxManager.setOnWaveKeyPressed(24000){ }

        rxManager.acousticSensor = AcousticSensor {
            _isListening.postValue(false)
            _isProcessing.postValue(false)
            if(it.startsWith("@")){
                val response = FormatUtil.jsonToAccountInfo(key, it)

                if(!response.equals("")){
                    _accountInfo.value = Received(
                        0,
                        response.bank_id,
                        response.accountNickname,
                        response.accountNumber,
                        response.speakerNickName,
                        response.speakerIcon,
                    )
                }
            } else {
                val response = FormatUtil.jsonToAccountInfo(it)

                _accountInfo.value = Received(
                    0,
                    response.bank_id,
                    response.accountNickname,
                    response.accountNumber,
                    response.speakerNickName,
                    response.speakerIcon,
                )
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
