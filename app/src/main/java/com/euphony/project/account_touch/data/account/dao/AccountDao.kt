package com.euphony.project.account_touch.data.account.dao

import androidx.room.*
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.global.AccountWithBank
import com.euphony.project.account_touch.utils.model.Color
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface AccountDao {
    @Transaction
    @Query("SELECT * FROM account ORDER BY create_date DESC")
    fun findAllBy(): Flow<List<AccountWithBank>>

    @Query("SELECT * FROM account WHERE bank_id = :bankId ORDER BY create_date DESC")
    fun findAllByBankId(bankId: Long): Flow<List<Account>>

    @Query("SELECT * FROM account WHERE account_id = :id")
    fun findById(id: Long): Flow<Account>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: Account): Long

    @Query("UPDATE account SET color = :modifyColor, modify_date = :modifyDate WHERE account_id =:id")
    suspend fun update(id: Long, modifyColor: Color, modifyDate: Date = Date(System.currentTimeMillis()))

    @Delete
    suspend fun delete(account: Account)
}
