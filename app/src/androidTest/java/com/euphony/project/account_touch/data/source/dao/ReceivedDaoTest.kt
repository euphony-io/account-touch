package com.euphony.project.account_touch.data.source.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.euphony.project.account_touch.data.bank.dao.BankDao
import com.euphony.project.account_touch.data.received.dao.ReceivedDao
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.data.received.entity.Received
import com.euphony.project.account_touch.utils.model.BankIcon
import com.euphony.project.account_touch.utils.model.ExternalPackage
import com.euphony.project.account_touch.utils.model.UserIcon
import com.euphony.project.account_touch.data.global.config.EuphonyDatabase
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@RunWith(AndroidJUnit4::class)
class ReceivedDaoTest :TestCase(){
    private lateinit var dao: ReceivedDao
    private lateinit var bankDao: BankDao
    private lateinit var db: EuphonyDatabase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    public override fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, EuphonyDatabase::class.java)
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor()).build()

        dao = db.getReceivedDao()
        bankDao = db.getBankDao()
    }

    companion object {
        val bank = Bank(1L, "국민은행", BankIcon.KB, 12, ExternalPackage.KOOKMIN)
        val _received = fun(bankId: Long): Received{
            return Received(1L,  bankId,"도영이의 국민 계좌", "123123123123", "은빈", UserIcon.GHOST)
        }
        val _receivedList = fun(bankId: Long): List<Received>{
            return listOf(
                Received(1L, bankId,"도영이의 국민 계좌", "123123123123", "은빈",
                    UserIcon.GHOST, Date(2020,12,12)),
                Received(2L, bankId, "도영이의 하나 계좌", "32132132123", "은빈",
                    UserIcon.GHOST, Date(2021,12,12)),
                Received(3L, bankId, "도영이의 카카오 계좌", "34532153", "은빈",
                    UserIcon.GHOST, Date(2022,12,12))
            )
        }
    }

    @Test
    fun 받은_계좌_생성() =  testScope.runBlockingTest {
        //given
        val bankId = bankDao.insert(bank)
        val received = Received(1L, bankId, "도영이의 국민 계좌", "123123123123", "은빈", UserIcon.GHOST)

        //when
        val newId = dao.insert(received)

        //then
        assertThat(received.id).isEqualTo(newId)
    }

    @Test
    fun 받은_계좌_리스트_조회() =  testScope.runBlockingTest {
        //given
        val bankId = bankDao.insert(bank)
        val receivedList = _receivedList(bankId)
        receivedList.forEach { received ->
            dao.insert(received)
        }

        //when
        val receivedNewList = dao.findAllBy();

        //then
        receivedNewList.take(1).collect{ received ->
            assertThat(received.first()?.id).isEqualTo(receivedList[2].id)
            assertThat(received.last()?.id).isEqualTo(receivedList[0].id)
        }
    }

    @Test
    fun 받은_계좌_상세_조회() =  testScope.runBlockingTest {
        //given
        val bankId = bankDao.insert(bank)
        val received = _received(bankId);
        dao.insert(received)

        //when
        val findReceived = dao.findById(received.id);

        //then
        assertThat(findReceived.id).isEqualTo(received.id)
    }

    @Test
    fun 받은_계좌_삭제() =  testScope.runBlockingTest {
        //given
        val bankId = bankDao.insert(bank)
        val received = _received(bankId);
        dao.insert(received)

        //when
        dao.delete(received);
        val receivedNewList = dao.findAllBy().filterNotNull()
        //then
        receivedNewList.take(1).collect{ received ->
            assertThat(received).isEmpty()
        }
    }


    @After
    @Throws(IOException::class)
    fun cleanup() {
        db.close()
    }

}