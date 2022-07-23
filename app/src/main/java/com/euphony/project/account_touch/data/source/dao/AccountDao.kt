package com.euphony.project.account_touch.data.source.dao

import androidx.room.*
import com.euphony.project.account_touch.data.entity.Account
import com.euphony.project.account_touch.data.entity.model.Color
import java.util.*

@Dao
interface AccountDao {
    @Query("SELECT * FROM account ORDER BY is_allow_any DESC, create_date DESC")
    suspend fun getAll(): List<Account>

    @Query("SELECT * FROM account WHERE account_id = :id")
    suspend fun getAccountById(id: Long): Account

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(account: Account): Long

    @Query("UPDATE account SET is_allow_any = :isAllowAny, is_always_on = :isAlwaysOn, color = :modifyColor," +
            "modify_date = :modifyDate WHERE account_id =:id")
    suspend fun modifyAccount(id: Long, isAllowAny: Boolean, isAlwaysOn: Boolean, modifyColor: Color,
                              modifyDate: Date = Date(System.currentTimeMillis()))

    @Delete
    suspend fun deleteAccount(account: Account)
}