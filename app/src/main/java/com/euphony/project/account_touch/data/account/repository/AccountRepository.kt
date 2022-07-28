package com.euphony.project.account_touch.data.account.repository

import androidx.annotation.WorkerThread
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.account.dao.AccountDao
import com.euphony.project.account_touch.data.account.dto.CreateAccountRequest
import com.euphony.project.account_touch.data.account.dto.UpdateAccountRequest
import com.euphony.project.account_touch.data.global.AccountWithBank
import kotlinx.coroutines.flow.Flow

class AccountRepository(private val dao : AccountDao){

    //getAccounts
    fun getAccounts(): Flow<List<AccountWithBank>>{
        return dao.findAllBy()
    }

    //getAccountById
    fun getAccount(id: Long): Flow<Account>{
        return dao.findById(id)
    }

    //insert
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createAt(request: CreateAccountRequest) {
        dao.insert(request.toEntity())
    }

    //update
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun modifyAccount(request: UpdateAccountRequest) {
        dao.update(
            id=request.id,
            isAlwaysOn=request.isAlwaysOn,
            modifyColor=request.color,
        )
    }

    //delete
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAccount(account: Account) {
        dao.delete(account);
    }
}