package com.euphony.project.account_touch.data.global.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.data.global.converter.DateConverter
import com.euphony.project.account_touch.data.account.dao.AccountDao
import com.euphony.project.account_touch.data.bank.dao.BankDao
import com.euphony.project.account_touch.data.received.dao.ReceivedDao
import com.euphony.project.account_touch.data.user.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(
    entities = [
        (User::class),
        (Account::class),
        (Received::class),
        (Bank::class)
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class EuphonyDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getAccountDao(): AccountDao
    abstract fun getReceivedDao(): ReceivedDao
    abstract fun getBankDao(): BankDao

    companion object {

        @Volatile
        private var INSTANCE: EuphonyDatabase? = null

        fun fillInDb(context: Context){
            CoroutineScope(Dispatchers.IO).launch {
                getInstance(context).getBankDao().insertAll(BANK_DATA)
            }
        }
        fun getInstance(context: Context): EuphonyDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        EuphonyDatabase::class.java,
                        "euphony_db"
                    )
                    .addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            fillInDb(context.applicationContext)
                        }
                    })
                    .setQueryCallback(QueryCallback {
                            sqlQuery, bindArgs ->  println("SQL Query: $sqlQuery SQL Args: $bindArgs")
                        }, Executors.newSingleThreadExecutor())
                    .fallbackToDestructiveMigration()
                    .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}