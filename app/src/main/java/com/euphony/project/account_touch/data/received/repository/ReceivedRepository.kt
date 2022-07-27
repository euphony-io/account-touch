package com.euphony.project.account_touch.data.received.repository

import androidx.annotation.WorkerThread
import com.euphony.project.account_touch.data.account.dto.CreateAccountRequest
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.received.dao.ReceivedDao
import com.euphony.project.account_touch.data.received.dto.CreateReceivedRequest
import com.euphony.project.account_touch.data.received.entity.Received
import kotlinx.coroutines.flow.Flow

class ReceivedRepository(private val dao : ReceivedDao) {

    fun getReceivedList(): Flow<List<Received?>> {
        return dao.findAllBy()
    }

    fun getReceived(id: Long): Received {
        return dao.findById(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createAt(request: CreateReceivedRequest) {
        dao.insert(request.toEntity())
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteReceived(received: Received){
        dao.delete(received)
    }
}