package com.euphony.project.account_touch.data.source.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.euphony.project.account_touch.data.entity.Account
import com.euphony.project.account_touch.data.entity.model.Color
import com.euphony.project.account_touch.data.source.EuphonyDatabase
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AccountDaoTest : TestCase() {

    private lateinit var dao: AccountDao
    private lateinit var db: EuphonyDatabase

    @Before
    public override fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            appContext,
            EuphonyDatabase::class.java
        ).build()

        dao = db.getAccountDao()
    }

    @Test
    fun 계좌_생성() = runBlocking {
        //given
        val nickname = "은빈이의 국민은행 계좌임돠"
        val accountNumber = "123123123"
        val account = Account(
            1L,
            nickname,
            accountNumber,
            false,
            true,
            Color.APRICOT
        )

        //when
        dao.addAccount(account)
        val newAccount = dao.getAll()[0]

        //then
        assertThat(newAccount.nickname).isEqualTo(nickname)
        assertThat(newAccount.accountNumber).isEqualTo(accountNumber)
        assertThat(newAccount.color).isEqualTo(Color.APRICOT)
    }

    @Test
    fun 계좌_수정() = runBlocking {
        //given
        val account = Account(
            1L,
            "은빈이의 국민은행 계좌임돠",
            "123123123",
            isAllowAny = false,
            isAlwaysOn = false,
            Color.BLUE
        )
        dao.addAccount(account)

        val modifyIsAllowAny = true
        val modifyIsAlwaysOn = true

        //when
        dao.modifyAccount(1L, modifyIsAllowAny, modifyIsAlwaysOn, Color.GREEN)
        val modifyAccount = dao.getAll()[0]

        //then
        assertThat(modifyAccount.isAllowAny).isEqualTo(modifyIsAllowAny)
        assertThat(modifyAccount.isAlwaysOn).isEqualTo(modifyIsAlwaysOn)
        assertThat(modifyAccount.color).isEqualTo(Color.GREEN)
    }

    @Test
    fun 계좌_조회_정렬() = runBlocking {
        //given
        val account1 = Account(1L,
            "은빈이의 국민은행 계좌임돠", "123321123", isAllowAny = false, isAlwaysOn = false, Color.BLACK)
        val account2 = Account(2L,
            "은빈이의 하나은행 계좌임돠", "343463456", isAllowAny = false, isAlwaysOn = false, Color.BLACK)
        val account3 = Account(3L,
            "은빈이의 농협은행 계좌임돠", "12313543", isAllowAny = false, isAlwaysOn = false, Color.BLACK)

        dao.addAccount(account1)
        dao.addAccount(account2)
        dao.addAccount(account3)

        //when
        val accountList = dao.getAll()

        //then
        assertThat(accountList[0].id).isEqualTo(2L)
        assertThat(accountList[2].id).isEqualTo(3L)
    }

    @Test
    fun 계좌_삭제() = runBlocking {
        //given
        val account = Account(
            1L,
            "은빈이의 국민은행 계좌임돠",
            "123123123",
            isAllowAny = false,
            isAlwaysOn = false,
            Color.BLUE
        )
        dao.addAccount(account)

        //when
        dao.deleteAccount(account)
        val accountList = dao.getAll()

        //then
        assertThat(accountList).isEmpty()
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
        db.close()
    }
}