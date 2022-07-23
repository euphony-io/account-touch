package com.euphony.project.account_touch.data.source.dao

import androidx.room.*
import com.euphony.project.account_touch.data.entity.Received
import java.util.*

@Dao
interface ReceivedDao {
    @Query("SELECT * FROM received ORDER BY create_date DESC")
    suspend fun getAll(): List<Received>

    @Query("SELECT * FROM received WHERE received_id = :id")
    suspend fun getReceivedById(id: Long): Received

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReceived(received: Received): Long

    @Delete
    suspend fun deleteReceived(received: Received)
}