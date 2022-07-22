package com.euphony.project.account_touch.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="account")
data class Account(
    @NonNull
    @ColumnInfo(name = "account_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name = "account_number")
    var accountNumber: Long,

    @ColumnInfo(name = "is_allow_any")
    var isAllowAny: Boolean,

    @ColumnInfo(name = "is_always_on")
    var isAlwaysOn: Boolean,

    @ColumnInfo(name = "create_date")
    override var createDate: Date,

    @ColumnInfo(name = "modify_date")
    override var modifyDate: Date,

    ) : BaseEntity()