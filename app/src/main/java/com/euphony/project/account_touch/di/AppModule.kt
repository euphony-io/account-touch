package com.euphony.project.account_touch.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.euphony.project.account_touch.data.account.dao.AccountDao
import com.euphony.project.account_touch.data.account.repository.AccountRepository
import com.euphony.project.account_touch.data.bank.dao.BankDao
import com.euphony.project.account_touch.data.bank.repository.BankRepository
import com.euphony.project.account_touch.data.global.config.EuphonyDatabase
import com.euphony.project.account_touch.data.received.dao.ReceivedDao
import com.euphony.project.account_touch.data.received.repository.ReceivedRepository
import com.euphony.project.account_touch.data.user.dao.UserDao
import com.euphony.project.account_touch.data.user.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): EuphonyDatabase
        = EuphonyDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideAccountDao(database: EuphonyDatabase): AccountDao = database.getAccountDao();

    @Singleton
    @Provides
    fun provideUserDao(database: EuphonyDatabase): UserDao = database.getUserDao();

    @Singleton
    @Provides
    fun provideBankDao(database: EuphonyDatabase): BankDao = database.getBankDao();

    @Singleton
    @Provides
    fun provideReceivedDao(database: EuphonyDatabase): ReceivedDao = database.getReceivedDao();

    @Singleton
    @Provides
    fun provideAccountRepository(dao: AccountDao): AccountRepository = AccountRepository(dao)

    @Singleton
    @Provides
    fun provideUserRepository(dao: UserDao): UserRepository = UserRepository(dao)

    @Singleton
    @Provides
    fun provideBankRepository(dao: BankDao): BankRepository = BankRepository(dao)

    @Singleton
    @Provides
    fun provideReceivedRepository(dao: ReceivedDao): ReceivedRepository = ReceivedRepository(dao)
}