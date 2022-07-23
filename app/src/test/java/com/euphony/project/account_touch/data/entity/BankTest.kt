package com.euphony.project.account_touch.data.entity

import com.euphony.project.account_touch.data.entity.model.BankIcon
import com.euphony.project.account_touch.data.entity.model.ExternalPackage
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class BankTest {

    @Test
    fun 은행_객체를_생성한다(){
        //when
        val bank = Bank(1L, "국민은행", BankIcon.KB, 12, ExternalPackage.KOOKMIN);

        //then
        assertThat(bank).isNotNull()
        assertThat(bank.bankIconPath).isEqualTo(BankIcon.KB)
        assertThat(bank.appExternalPackage).isEqualTo(ExternalPackage.KOOKMIN)
    }

    @Test
    fun 은행_객체를_조회한다(){
        //given
        val bankLength = 100
        val bankList = arrayOf(
            Bank(1L, "국민은행", BankIcon.KB, bankLength, ExternalPackage.KOOKMIN),
            Bank(2L, "케이뱅크", BankIcon.KBANK, 12, ExternalPackage.KBANK)
        )

        //when
        val bank = bankList[0]

        //then
        assertThat(bank.accountLength).isEqualTo(bankLength);
    }
}