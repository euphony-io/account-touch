package com.euphony.project.account_touch.data.source.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.euphony.project.account_touch.data.entity.UserEntity
import com.euphony.project.account_touch.data.source.EuphonyDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class UserDaoTest : TestCase() {

    private lateinit var dao: UserDao
    private lateinit var db: EuphonyDatabase

    @Before
    public override fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            appContext,
            EuphonyDatabase::class.java
        ).build()


        dao = db.getUserDao()
    }

    @Test
    fun 유저_생성() = runBlocking {
        //given
        val user = UserEntity(1,"은빈", Date(), Date())

        //when
        dao.addUser(user)
        val newUser = dao.getUserById(1);

        //then
        assertThat(newUser).isEqualTo(user)
    }

    @Test
    fun 유저_정보_수정() = runBlocking {
        //given
        val modifyNickname = "은빈빈";
        val user = UserEntity(1,"은빈",Date(), Date())
        dao.addUser(user)

        //when
        dao.modifyUser(1, "은빈빈")
        val newUser = dao.getUserById(1)

        //then
        assertThat(newUser.nickname).isEqualTo(modifyNickname)
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
        db.close()
    }
}