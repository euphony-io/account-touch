package com.euphony.project.account_touch.data.source.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.euphony.project.account_touch.data.bank.dao.BankDao
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.utils.model.BankIcon
import com.euphony.project.account_touch.utils.model.ExternalPackage
import com.euphony.project.account_touch.data.global.config.EuphonyDatabase
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@RunWith(AndroidJUnit4::class)
class BankDaoTest  : TestCase() {

    private lateinit var dao: BankDao
    private lateinit var db: EuphonyDatabase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    public override fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, EuphonyDatabase::class.java)
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()

        dao = db.getBankDao()
    }

    @Test
    fun 은행_생성() = testScope.runBlockingTest {
        //given
        val bankName = "국민은행"
        val bank = Bank(1L, bankName, BankIcon.KB, 12, ExternalPackage.KOOKMIN)

        //when
        dao.insert(bank)
        //then

        dao.findAllBy().take(1).collect { bank ->
            assertThat(bank[0].bankIconPath).isNotNull()
            assertThat(bank[0].name).isEqualTo(bankName)
        }
    }

    @Test
    fun 은행_리스트_생성_조회() = testScope.runBlockingTest {
        //given
        val banks = arrayListOf<Bank>(
            Bank(1L, "국민은행", BankIcon.KB, 12, ExternalPackage.KOOKMIN),
            Bank(2L, "케이뱅크", BankIcon.KBANK, 12, ExternalPackage.KBANK),
            Bank(3L, "기업은행", BankIcon.IBK, 12, ExternalPackage.IBK),
            Bank(4L, "하나은행", BankIcon.KEB, 12, ExternalPackage.KEB)
        )
        dao.insertAll(banks)

        //when
        dao.findAllBy().take(1).collect { bank ->
            //then
            assertThat(bank.size).isEqualTo(4)
        }
    }

    @Test
    fun 은행_상세_조회() = testScope.runBlockingTest {
        //given
        val bankName = "국민은행"
        val bank =  Bank(1L, bankName, BankIcon.KB, 12, ExternalPackage.KOOKMIN)
        dao.insert(bank)

        //when
        val newBank = dao.findById(1L)

        //then
        assertThat(newBank).isNotNull()
        assertThat(newBank.name).isEqualTo(bankName)
    }

    @Test
    fun 은행_앱패키지_조회() = testScope.runBlockingTest {
        //given
        val ppackage = ExternalPackage.KAKAOPAY
        val bank =  Bank(1L, "카카오뱅크", BankIcon.KAKAOBANK, 12, ppackage)
        dao.insert(bank)

        //when
        val newPackage = dao.findAppPackageById(1L)

        //then
        assertThat(newPackage).isNotNull()
        assertThat(newPackage).isEqualTo(ppackage)
    }

    @After
    fun cleanup() {
        db.close()
    }
}