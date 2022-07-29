package com.euphony.project.account_touch.data.received.repository

import androidx.annotation.WorkerThread
import com.euphony.project.account_touch.data.received.dao.ReceivedDao
import com.euphony.project.account_touch.data.received.dto.CreateReceivedRequest
import com.euphony.project.account_touch.data.received.entity.Received
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReceivedRepository @Inject constructor (
    private val dao : ReceivedDao
) {

    fun getReceivedList(): Flow<List<Received>> {
        return dao.findAllBy()
    }

    suspend fun getReceived(id: Long): Received {
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