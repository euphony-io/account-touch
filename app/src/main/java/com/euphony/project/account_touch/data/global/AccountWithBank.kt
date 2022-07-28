package com.euphony.project.account_touch.data.global

import androidx.room.Embedded
import androidx.room.Relation
import com.euphony.project.account_touch.data.account.entity.Account
import com.euphony.project.account_touch.data.bank.entity.Bank

data class AccountWithBank (
    @Embedded
    val account: Account,

    @Relation(entity = Bank::class, parentColumn = "bank_id", entityColumn = "bank_id")
    val bank: Bank
)