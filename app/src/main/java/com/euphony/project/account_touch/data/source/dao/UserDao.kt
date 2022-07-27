package com.euphony.project.account_touch.data.source.dao

import androidx.room.*
import com.euphony.project.account_touch.data.entity.User
import com.euphony.project.account_touch.data.entity.model.UserIcon
import java.util.*

@Dao
interface UserDao {
    @Query("SELECT * from user WHERE user_id = :id")
    suspend fun getUserById(id: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User): Long

    @Query("UPDATE user SET nickname = :nickname, icon = :modifyIcon, modify_date = :modifyDate  WHERE user_id = :id")
    suspend fun modifyUser(id: Long, nickname: String, modifyIcon: UserIcon, modifyDate: Date = Date(System.currentTimeMillis()))
}