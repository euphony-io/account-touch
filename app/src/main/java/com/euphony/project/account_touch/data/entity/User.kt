package com.euphony.project.account_touch.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.euphony.project.account_touch.data.entity.model.UserIcon
import java.util.*

@Entity(tableName = "user")
data class User(
    @NonNull
    @ColumnInfo(name = "user_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name = "icon")
    val icon: UserIcon,

    @ColumnInfo(name = "create_date")
    override val createDate: Date = Date(System.currentTimeMillis()),

    @ColumnInfo(name = "modify_date")
    override val modifyDate: Date = Date(System.currentTimeMillis())

) : BaseEntity()