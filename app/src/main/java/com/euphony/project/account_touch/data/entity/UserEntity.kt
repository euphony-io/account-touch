package com.euphony.project.account_touch.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user")
data class UserEntity(
    @NonNull
    @ColumnInfo(name = "user_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "nickname")
    var nickname: String,

    @ColumnInfo(name = "create_date")
    override var createDate: Date = Date(System.currentTimeMillis()),

    @ColumnInfo(name = "modify_date")
    override var modifyDate: Date = Date(System.currentTimeMillis())

) : BaseEntity() {

}
