package com.euphony.project.account_touch.data.entity

import com.euphony.project.account_touch.data.received.entity.Received
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ReceivedTest {

    @Test
    fun 받은계좌_객체를_생성한다(){
        //given
        val accountNumber = "123123123123"

        //then
        val received = Received(1L,1L, accountNumber)

        //then
        assertThat(received.accountNumber).isEqualTo(accountNumber)
    }

    @Test
    fun 받은계좌_객체를_상세_조회한다(){
        //given
        val findReceived = Received(3L, 1L,"15651232458")

        val receivedList = listOf<Received>(
            Received(1L, 1L, "15651232458" ),
            Received(2L, 1L,"15651232458"),
            findReceived
        )

        //then
        assertThat(receivedList.contains(findReceived)).isTrue()
    }

}