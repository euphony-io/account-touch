package com.euphony.project.account_touch.data.bank.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.utils.model.ExternalPackage
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDao {
    @Query("SELECT * FROM bank ORDER BY bank_id")
    fun findAllBy(): Flow<List<Bank>>

    @Query("SELECT * FROM bank WHERE bank_id = :id")
    fun findById(id: Long): Bank

    @Query("SELECT app_package FROM bank WHERE bank_id = :id")
    fun findAppPackageById(id: Long): ExternalPackage

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bank: Bank) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(banks: List<Bank>) : List<Long>
}