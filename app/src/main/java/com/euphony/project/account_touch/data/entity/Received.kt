package com.euphony.project.account_touch.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.euphony.project.account_touch.data.entity.model.UserIcon
import java.util.*

@Entity(tableName = "received")
data class Received(
    @NonNull
    @ColumnInfo(name = "received_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "account_nickname")
    var accountNickname: String,

    @ColumnInfo(name = "account_number")
    var accountNumber: String,

    @ColumnInfo(name = "speaker_nickname")
    var speakerNickName: String,

    @ColumnInfo(name = "speaker_icon")
    var speakerIcon: UserIcon,

    @ColumnInfo(name = "create_date")
    override var createDate: Date = Date(System.currentTimeMillis()),

    @ColumnInfo(name = "modify_date")
    override var modifyDate: Date = Date(System.currentTimeMillis()),

    ) : BaseEntity()
