package com.euphony.project.account_touch.data.entity

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*

internal class UserEntityTest{

    @Test
    fun 유저_객체를_생성한다(){
        //given
        val user = UserEntity(1L, "은빈", Date(), Date())

        //then
        assertThat(user.id).isEqualTo(1L)
        assertThat(user.nickname).isEqualTo("은빈")
    }
}