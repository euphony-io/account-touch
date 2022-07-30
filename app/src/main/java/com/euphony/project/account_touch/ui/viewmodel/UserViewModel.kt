package com.euphony.project.account_touch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.euphony.project.account_touch.data.user.dto.CreateUserRequest
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.data.user.repository.UserRepository
import com.euphony.project.account_touch.utils.model.UserIcon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor (
    private val repository: UserRepository
) : ViewModel() {

    val user: LiveData<User> = repository.getUser(1).asLiveData()

    fun addUser(request: CreateUserRequest) = viewModelScope.launch {
        repository.createAt(request)
    }

    fun modifyUser(id: Long, nickname: String, modifyIcon: UserIcon) = viewModelScope.launch {
        repository.updateUser(id, nickname, modifyIcon)
    }

}
