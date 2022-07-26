package com.euphony.project.account_touch.data.entity.data

import com.euphony.project.account_touch.data.entity.Bank
import com.euphony.project.account_touch.data.entity.model.BankIcon
import com.euphony.project.account_touch.data.entity.model.ExternalPackage

val BANK_DATA = arrayListOf(
    Bank(0L, "부산은행", BankIcon.BNK, 12),
    Bank(0L, "시티은행", BankIcon.CITI, 12),
    Bank(0L, "대구은행", BankIcon.DGB, 12),
    Bank(0L, "제주은행", BankIcon.JEJU, 12),
    Bank(0L, "KDB은행", BankIcon.KDB, 12),
    Bank(0L, "광주은행", BankIcon.KJ, 12),
    Bank(0L, "새마을금고", BankIcon.MG, 12),
    Bank(0L, "제일은행", BankIcon.SC, 12),
    Bank(0L, "수협은행", BankIcon.SH, 12),
    Bank(0L, "신한은행", BankIcon.SINHAN, 12),
    Bank(0L, "카카오뱅크", BankIcon.KAKAOBANK, 12, ExternalPackage.KAKAOBANK),
    Bank(0L, "케이뱅크", BankIcon.KBANK, 12, ExternalPackage.KBANK),
    Bank(0L, "국민은행", BankIcon.KB, 12, ExternalPackage.KOOKMIN),
    Bank(0L, "하나은행", BankIcon.KEB, 12, ExternalPackage.KEB),
    Bank(0L, "기업은행", BankIcon.IBK, 12, ExternalPackage.IBK),
    Bank(0L, "농협은행", BankIcon.NH, 12, ExternalPackage.NH),
    Bank(0L, "신협은행", BankIcon.SINHYEOP, 12, ExternalPackage.SINHYEOB),
    Bank(0L, "우리은행", BankIcon.WOORI, 12, ExternalPackage.WOORI),
)