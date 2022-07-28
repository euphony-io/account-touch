package com.euphony.project.account_touch.data.entity


import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.utils.model.Color
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class AccountTest{

    @Test
    fun 계좌_객체를_생성한다(){
        //given
        val nickname = "은빈이의 국민은행 계좌"
        val accountNumber = "123123123123"

        //then
        val account = Account(
            1L,
            1L,
            nickname,
            accountNumber,
            false,
            Color.BLACK
        )

        //then
        assertThat(account.nickname).isEqualTo(nickname)
        assertThat(account.accountNumber).isEqualTo(accountNumber)
        assertThat(account.color).isEqualTo(Color.BLACK)
    }
}