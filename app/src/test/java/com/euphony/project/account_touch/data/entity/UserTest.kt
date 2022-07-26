package com.euphony.project.account_touch.data.entity

import com.euphony.project.account_touch.data.entity.model.UserIcon
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class UserTest{

    @Test
    fun 유저_객체를_생성한다(){
        //given
        val user = User(1L, "은빈", UserIcon.GHOST)

        //then
        assertThat(user.id).isEqualTo(1L)
        assertThat(user.nickname).isEqualTo("은빈")
        assertThat(user.icon).isEqualTo(UserIcon.GHOST)
    }
}