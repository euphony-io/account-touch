package com.euphony.project.account_touch.ui.viewmodel

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import com.euphony.project.account_touch.data.received.dto.CreateReceivedRequest
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.data.received.repository.ReceivedRepository
import com.euphony.project.account_touch.data.user.dto.CreateUserRequest
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.data.user.repository.UserRepository
import com.euphony.project.account_touch.utils.model.UserIcon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ReceivedViewModel @Inject constructor (
    private val repository: ReceivedRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val allReceived: LiveData<List<Received>> = repository.getReceivedList().asLiveData()
    val user: LiveData<User> = userRepository.getUser(1).asLiveData()

    fun getReceived(id: Long) = repository.getReceived(id).asLiveData()

    fun addReceived(request: CreateReceivedRequest) = viewModelScope.launch {
        repository.createAt(request)
    }

    fun deleteReceived(received: Received) = viewModelScope.launch {
        repository.deleteReceived(received)
    }
}
