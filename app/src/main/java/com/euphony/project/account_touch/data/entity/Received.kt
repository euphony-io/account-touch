package com.euphony.project.account_touch.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.euphony.project.account_touch.data.entity.model.UserIcon
import java.util.*

@Entity(
    tableName = "received",
    foreignKeys = [ForeignKey(
        entity = Bank::class,
        parentColumns = arrayOf("bank_id"),
        childColumns = arrayOf("bank"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Received(
    @NonNull
    @ColumnInfo(name = "received_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "bank", index = true)
    val bank: Long,

    @ColumnInfo(name = "account_nickname")
    val accountNickname: String,

    @ColumnInfo(name = "account_number")
    val accountNumber: String,

    @ColumnInfo(name = "speaker_nickname")
    val speakerNickName: String,

    @ColumnInfo(name = "speaker_icon")
    val speakerIcon: UserIcon,

    @ColumnInfo(name = "create_date")
    override val createDate: Date = Date(System.currentTimeMillis()),

    @ColumnInfo(name = "modify_date")
    override val modifyDate: Date = Date(System.currentTimeMillis()),

) : BaseEntity()
