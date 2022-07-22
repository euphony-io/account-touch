package com.euphony.project.account_touch.data.entity


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*

internal class AccountTest{

    @Test
    fun 계좌_객체를_생성한다(){
        //given
        val nickname = "은빈이의 국민은행 계좌"
        val accountNumber = 123123123123

        //then
        val account = Account(
            1L,
            nickname,
            accountNumber,
            false,
            true,
            Date(),
            Date()
        )

        //then
        assertThat(account.nickname).isEqualTo(nickname)
        assertThat(account.accountNumber).isEqualTo(accountNumber)
    }

    @Test
    fun 계좌_객체를_수정한다(){
        //given
        val account = Account(
            1L,
            "은빈이의 국민은행 계좌",
            123123123123,
            false,
            true,
            Date(),
            Date()
        )

        //then
        account.isAllowAny = true

        //then
        assertThat(account.isAllowAny).isTrue()
    }
}