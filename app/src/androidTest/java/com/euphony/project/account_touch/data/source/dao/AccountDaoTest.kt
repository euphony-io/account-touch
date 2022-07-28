package com.euphony.project.account_touch.data.source.dao

import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.euphony.project.account_touch.data.account.dao.AccountDao
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.bank.dao.BankDao
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.data.global.AccountWithBank
import com.euphony.project.account_touch.utils.model.BankIcon
import com.euphony.project.account_touch.utils.model.Color
import com.euphony.project.account_touch.utils.model.ExternalPackage
import com.euphony.project.account_touch.data.global.config.EuphonyDatabase
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@RunWith(AndroidJUnit4::class)
class AccountDaoTest : TestCase() {

    private lateinit var dao: AccountDao
    private lateinit var bankDao: BankDao
    private lateinit var db: EuphonyDatabase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    companion object {
        val bank = Bank(1L, "국민은행", BankIcon.KB, 12, ExternalPackage.KOOKMIN)
        val accountList = listOf<Account>(
            Account(1L,1L, "은빈이의 국민은행", "123321123", isAlwaysOn = false, Color.GREEN),
            Account(2L,1L, "은빈이의 하나은행", "343463456", isAlwaysOn = false, Color.BLACK),
            Account(3L,1L, "은빈이의 농협은행", "12313543", isAlwaysOn = false, Color.BLACK)
        )
    }

    @Before
    public override fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context,  EuphonyDatabase::class.java)
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()

        dao = db.getAccountDao()
        bankDao = db.getBankDao()
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
        db.close()
    }


    @Test
    fun 계좌_생성() = testScope.runBlockingTest {
        //given
        val bankId = bankDao.insert(bank)

        val nickname = "은빈이의 국민은행 계좌임돠"
        val accountNumber = "123123123"
        val account = Account(1L, bankId, nickname, accountNumber, isAlwaysOn = true, Color.APRICOT)

        //when
        dao.insert(account)

        //then
        dao.findAllBy().take(1).collect { account ->
            assertThat(account[0].account?.nickname).isEqualTo(nickname)
            assertThat(account[0].account?.accountNumber).isEqualTo(accountNumber)
        }
    }

    @Test
    fun 계좌_수정() = testScope.runBlockingTest {
        //given
        bankDao.insert(bank)

        val modifyIsAlwaysOn = true
        val account = accountList[0]
        dao.insert(account)

        //when
        dao.update(1L, modifyIsAlwaysOn, Color.GREEN)

        //then
        dao.findAllBy().take(1).collect { account ->
            assertThat(account[0].account?.isAlwaysOn).isEqualTo(modifyIsAlwaysOn)
            assertThat(account[0].account?.color).isEqualTo(Color.GREEN)
        }
    }

    @Test
    fun 계좌_조회_정렬() = testScope.runBlockingTest {
        //given
        bankDao.insert(bank)
        accountList.forEach { account -> dao.insert(account) }

        //when
        val getList = dao.findAllBy()

        //then
        assertThat(getList).isNotNull()
        assertThat(getList.toList().size).isEqualTo(3)
    }

    @Test
    fun 계좌_삭제() = testScope.runBlockingTest {
        //given
        bankDao.insert(bank)
        dao.insert(accountList[0])

        //when
        dao.delete(accountList[0])

        //then
        val getList = dao.findAllBy()

        assertThat(getList.toList()).isEmpty()
    }


}