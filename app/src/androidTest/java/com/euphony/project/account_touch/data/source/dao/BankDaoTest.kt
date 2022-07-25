package com.euphony.project.account_touch.data.source.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.euphony.project.account_touch.data.entity.Bank
import com.euphony.project.account_touch.data.entity.model.BankInfo
import com.euphony.project.account_touch.data.entity.model.ProfileIcon
import com.euphony.project.account_touch.data.entity.model.ExternalPackage
import com.euphony.project.account_touch.data.source.TempDatabase
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BankDaoTest  : TestCase() {

    private lateinit var dao: BankDao
    private lateinit var db: TempDatabase

    @Before
    public override fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            appContext,
            TempDatabase::class.java
        ).build()

        dao = db.getBank()
    }

    @Test
    fun 은행_생성_조회() = runBlocking {
        //given
        val banks = arrayOf(
            Bank(1L, "국민은행", BankInfo.KB, 12, ExternalPackage.KOOKMIN),
            Bank(2L, "케이뱅크",BankInfo.KBANK, 12, ExternalPackage.KBANK),
            Bank(3L, "기업은행", BankInfo.IBK, 12, ExternalPackage.IBK),
            Bank(4L, "하나은행", BankInfo.KEB, 12, ExternalPackage.HANA),
            Bank(5L, "카카오페이", BankInfo.KAKAOPAY, 12, ExternalPackage.KAKAOPAY)
        )

        //when
        val iterator = banks.iterator()
        iterator.forEach { _bank ->
            dao.addBank(_bank)
        }

        val newBanks = dao.getAll()

        //then
        assertThat(newBanks.size).isEqualTo(banks.size)
        assertThat(newBanks[0]).isEqualTo(banks[0])
    }

    @Test
    fun 은행_상세_조회() = runBlocking {
        //given
        val bankName = "국민은행"
        val bank =  Bank(1L, bankName, BankInfo.KB, 12, ExternalPackage.KOOKMIN)
        dao.addBank(bank)

        //when
        val newBank = dao.getBankById(1L)

        //then
        assertThat(newBank).isNotNull()
        assertThat(newBank.name).isEqualTo(bankName)
    }

    @Test
    fun 은행_앱패키지_조회() = runBlocking {
        //given
        val ppackage = ExternalPackage.KAKAOPAY
        val bank =  Bank(1L, "카카오페이", BankInfo.KAKAOPAY, 12, ppackage)
        dao.addBank(bank)

        //when
        val newPackage = dao.getAppPackageById(1L)

        //then
        assertThat(newPackage).isNotNull()
        assertThat(newPackage).isEqualTo(ppackage)
    }

    @After
    fun cleanup() {
        db.close()
    }
}