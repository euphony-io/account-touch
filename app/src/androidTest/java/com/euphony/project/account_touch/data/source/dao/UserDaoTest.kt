package com.euphony.project.account_touch.data.source.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.euphony.project.account_touch.data.user.entity.User
import com.euphony.project.account_touch.utils.model.UserIcon
import com.euphony.project.account_touch.data.global.config.EuphonyDatabase
import com.euphony.project.account_touch.data.user.dao.UserDao
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@RunWith(AndroidJUnit4::class)
class UserDaoTest : TestCase() {

    private lateinit var dao: UserDao
    private lateinit var db: EuphonyDatabase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    companion object {
        val user = User(1,"은빈",  UserIcon.GHOST)
    }

    @Before
    public override fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, EuphonyDatabase::class.java)
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()

        dao = db.getUserDao()
    }

    @Test
    fun 유저_생성() = testScope.runBlockingTest {
        //when
        dao.insert(user)
        val newUser = dao.findById(1);

        //then
        newUser.take(1).collect{ user ->
            assertThat(user).isEqualTo(user)
        }
    }

    @Test
    fun 유저_정보_수정() = testScope.runBlockingTest {
        //given
        val modifyNickname = "은빈빈";
        dao.insert(user)

        //when
        dao.update(1, modifyNickname, UserIcon.CRYING)
        val newUser = dao.findById(1)

        //then
        newUser.take(1).collect{ user ->
            assertThat(user.nickname).isEqualTo(modifyNickname)
        }
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
        db.close()
    }
}