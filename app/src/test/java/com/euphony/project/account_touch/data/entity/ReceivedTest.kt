package com.euphony.project.account_touch.data.entity

import com.euphony.project.account_touch.data.entity.model.UserIcon
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ReceivedTest {

    @Test
    fun 받은계좌_객체를_생성한다(){
        //given
        val accountNickname = "은빈이의 국민은행 계좌"
        val accountNumber = "123123123123"

        val speakerNickname = "은빈"
        val speakerIcon = UserIcon.GHOST

        //then
        val received = Received(1L, accountNickname, accountNumber, speakerNickname, speakerIcon)

        //then
        assertThat(received.accountNickname).isEqualTo(accountNickname)
        assertThat(received.accountNumber).isEqualTo(accountNumber)
        assertThat(received.speakerNickName).isEqualTo(speakerNickname)
        assertThat(received.speakerIcon).isEqualTo(speakerIcon)
    }

    @Test
    fun 받은계좌_객체를_상세_조회한다(){
        //given
        val findReceived = Received(3L, "은빈이의 국민은행 계좌", "123123123","은빈", UserIcon.GHOST)

        val receivedList = listOf<Received>(
            Received(1L, "은빈이의 하나은행 계좌", "2132112321","은빈", UserIcon.GHOST),
            Received(2L, "은빈이의 신한은행 계좌", "15651232458","은빈", UserIcon.GHOST),
            findReceived
        )

        //then
        assertThat(receivedList.contains(findReceived)).isTrue()
    }

}