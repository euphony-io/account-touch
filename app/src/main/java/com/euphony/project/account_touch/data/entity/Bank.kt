package com.euphony.project.account_touch.data.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.euphony.project.account_touch.data.entity.model.BankIcon
import com.euphony.project.account_touch.data.entity.model.ExternalPackage
import java.util.*

@Entity(tableName="bank")
data class Bank(
    @NonNull
    @ColumnInfo(name = "bank_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "icon_path")
    val bankIconPath: BankIcon,

    @ColumnInfo(name = "account_length")
    val accountLength: Int,

    @Nullable
    @ColumnInfo(name = "app_package")
    val appExternalPackage: ExternalPackage? = null,

    @ColumnInfo(name = "create_date")
    override val createDate: Date = Date(System.currentTimeMillis()),

    @ColumnInfo(name = "modify_date")
    override val modifyDate: Date = Date(System.currentTimeMillis())
) : BaseEntity()