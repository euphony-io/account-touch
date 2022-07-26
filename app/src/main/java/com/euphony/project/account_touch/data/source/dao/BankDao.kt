package com.euphony.project.account_touch.data.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.euphony.project.account_touch.data.entity.Bank
import com.euphony.project.account_touch.data.entity.model.ExternalPackage

@Dao
interface BankDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBank(bank: Bank) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBanks(banks: List<Bank>) : List<Long>

    @Query("SELECT * FROM bank ORDER BY bank_id")
    suspend fun getAll(): List<Bank>

    @Query("SELECT * FROM bank WHERE bank_id = :id")
    suspend fun getBankById(id: Long): Bank

    @Query("SELECT app_package FROM bank WHERE bank_id = :id")
    suspend fun getAppPackageById(id: Long): ExternalPackage
}