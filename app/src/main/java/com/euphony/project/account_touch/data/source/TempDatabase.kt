package com.euphony.project.account_touch.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.euphony.project.account_touch.data.entity.Bank
import com.euphony.project.account_touch.data.entity.converter.DateConverter
import com.euphony.project.account_touch.data.source.dao.BankDao

@Database(
    entities = [
        (Bank::class),
    ], version = 1
)
@TypeConverters(DateConverter::class)
abstract class TempDatabase : RoomDatabase(){

    abstract fun getBank(): BankDao

    companion object {
        private var INSTANCE: EuphonyDatabase? = null

        fun getInstance(context: Context): EuphonyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            EuphonyDatabase::class.java,
                            "euphony_static_db")
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}