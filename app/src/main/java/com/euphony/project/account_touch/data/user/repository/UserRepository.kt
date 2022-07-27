package com.euphony.project.account_touch.data.user.repository

import androidx.annotation.WorkerThread
import com.euphony.project.account_touch.data.user.dao.UserDao
import com.euphony.project.account_touch.data.user.dto.CreateUserRequest
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.utils.model.UserIcon
import kotlinx.coroutines.flow.Flow

class UserRepository(private val dao: UserDao) {

    fun getUser(id: Long): Flow<User> {
        return dao.findById(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createAt(request: CreateUserRequest) {
        dao.insert(request.toEntity())
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateUser(id: Long, nickname: String, modifyIcon: UserIcon){
        dao.update(id, nickname, modifyIcon)
    }
}

