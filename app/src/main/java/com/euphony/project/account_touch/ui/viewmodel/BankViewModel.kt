package com.euphony.project.account_touch.ui.viewmodel

import androidx.lifecycle.*
import com.euphony.project.account_touch.data.account.repository.AccountRepository
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.data.bank.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BankViewModel @Inject constructor (
    private val repository: BankRepository
): ViewModel() {

    val banks: LiveData<List<Bank>> = repository.getBanks().asLiveData()

}
