package com.euphony.project.account_touch.ui.viewmodel

import androidx.lifecycle.*
import com.euphony.project.account_touch.data.account.dto.CreateAccountRequest
import com.euphony.project.account_touch.data.account.dto.UpdateAccountRequest
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.account.repository.AccountRepository
import com.euphony.project.account_touch.data.global.AccountWithBank
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor (
    private val repository: AccountRepository
) : ViewModel() {

    val accounts: LiveData<List<AccountWithBank>> = repository.getAccounts().asLiveData()

    //getAccount
    fun getAccount(id: Long): LiveData<Account> {
        return repository.getAccount(id).asLiveData()
    }

    //addAccount
    fun addAccount(request: CreateAccountRequest) = viewModelScope.launch {
        repository.createAt(request)
    }

    //modifyAccount
    fun modifyAccount(request: UpdateAccountRequest) = viewModelScope.launch {
        repository.modifyAccount(request)
    }

    //deleteAccount
    fun deleteAccount(account: Account) = viewModelScope.launch {
        repository.deleteAccount(account)
    }
}