package com.euphony.project.account_touch.data.user.dao

import androidx.room.*
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.utils.model.UserIcon
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface UserDao {
    @Query("SELECT * from user WHERE user_id = :id")
    fun findById(id: Long): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("UPDATE user SET nickname = :nickname, icon = :modifyIcon, modify_date = :modifyDate  WHERE user_id = :id")
    suspend fun update(id: Long, nickname: String, modifyIcon: UserIcon, modifyDate: Date = Date(System.currentTimeMillis()))
}