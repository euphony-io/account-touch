package com.euphony.project.account_touch.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.euphony.project.account_touch.data.entity.Account
import com.euphony.project.account_touch.data.entity.Bank
import com.euphony.project.account_touch.data.entity.UserEntity
import com.euphony.project.account_touch.data.entity.converter.DateConverter
import com.euphony.project.account_touch.data.source.dao.AccountDao
import com.euphony.project.account_touch.data.source.dao.UserDao

@Database(
    entities = [
        (UserEntity::class),
        (Account::class),
        (Bank::class),
    ], version = 1
)
@TypeConverters(DateConverter::class)
abstract class EuphonyDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getAccountDao(): AccountDao

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
                            "euphony_db")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}