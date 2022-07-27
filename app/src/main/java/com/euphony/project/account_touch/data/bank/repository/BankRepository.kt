package com.euphony.project.account_touch.data.bank.repository

import androidx.annotation.WorkerThread
import com.euphony.project.account_touch.data.bank.dao.BankDao
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.utils.model.ExternalPackage
import kotlinx.coroutines.flow.Flow

class BankRepository(private val dao : BankDao) {

    fun getBanks(): Flow<List<Bank>> {
        return dao.findAllBy()
    }

    fun getBanksById(id: Long): Bank {
        return dao.findById(id)
    }

    fun getAppPackageById(id: Long): ExternalPackage {
        return dao.findAppPackageById(id)
    }
}