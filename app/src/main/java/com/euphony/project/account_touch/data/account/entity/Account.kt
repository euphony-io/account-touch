package com.euphony.project.account_touch.data.account.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.euphony.project.account_touch.data.bank.entity.Bank
import com.euphony.project.account_touch.data.global.base.entity.BaseEntity
import com.euphony.project.account_touch.utils.model.Color
import java.util.*

@Entity(
    tableName="account",
    foreignKeys = [ForeignKey(
        entity = Bank::class,
        parentColumns = arrayOf("bank_id"),
        childColumns = arrayOf("bank_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Account(
    @NonNull
    @ColumnInfo(name = "account_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @NonNull
    @ColumnInfo(name = "bank_id", index = true)
    val bank_id: Long,

    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name = "account_number")
    val accountNumber: String,

    @ColumnInfo(name = "is_always_on")
    val isAlwaysOn: Boolean,

    @ColumnInfo(name = "color")
    val color: Color,

    @ColumnInfo(name = "create_date")
    override val createDate: Date = Date(System.currentTimeMillis()),

    @ColumnInfo(name = "modify_date")
    override val modifyDate: Date = Date(System.currentTimeMillis()),

    ) : BaseEntity()