package com.euphony.project.account_touch.ui.viewmodel

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
import javax.inject.Inject

@HiltViewModel
class ReceivedViewModel @Inject constructor (
    private val repository: ReceivedRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val allReceived: LiveData<List<Received>> = repository.getReceivedList().asLiveData()
    val user: LiveData<User> = userRepository.getUser(0).asLiveData()

    suspend fun getReceived(id: Long): Received {
        return repository.getReceived(id)
    }

    fun addReceived(request: CreateReceivedRequest) = viewModelScope.launch {
        repository.createAt(request)
    }

    fun deleteReceived(received: Received) = viewModelScope.launch {
        repository.deleteReceived(received)
    }

}