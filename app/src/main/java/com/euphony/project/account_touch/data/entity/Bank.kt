package com.euphony.project.account_touch.data.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.euphony.project.account_touch.data.entity.model.BankInfo
import com.euphony.project.account_touch.data.entity.model.ExternalPackage
import java.util.*

@Entity(tableName="bank")
data class Bank(
    @NonNull
    @ColumnInfo(name = "bank_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "icon_path")
    var bankIconPath: BankInfo,

    @ColumnInfo(name = "account_length")
    var accountLength: Int,

    @Nullable
    @ColumnInfo(name = "app_package")
    var appExternalPackage: ExternalPackage? = null,

    @ColumnInfo(name = "create_date")
    override var createDate: Date = Date(System.currentTimeMillis()),

    @ColumnInfo(name = "modify_date")
    override var modifyDate: Date = Date(System.currentTimeMillis())
) : BaseEntity()