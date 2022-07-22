package com.euphony.project.account_touch.data.source.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.euphony.project.account_touch.data.entity.UserEntity
import java.util.*

@Dao
interface UserDao {
    @Query("SELECT * from user WHERE user_id = :id")
    suspend fun getUserById(id: Long): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity)

    @Query("UPDATE user SET nickname = :nickname, modify_date = :modifyDate  WHERE user_id = :id")
    suspend fun modifyUser(id: Long, nickname: String,  modifyDate: Date = Date())
}