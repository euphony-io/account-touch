package com.euphony.project.account_touch.data.received.dao

import androidx.room.*
import com.euphony.project.account_touch.data.received.entity.Received
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceivedDao {
    @Query("SELECT * FROM received ORDER BY create_date DESC")
    fun findAllBy(): Flow<List<Received>>

    @Query("SELECT * FROM received WHERE received_id = :id")
    suspend fun findById(id: Long): Received

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(received: Received): Long

    @Delete
    suspend fun delete(received: Received)
}