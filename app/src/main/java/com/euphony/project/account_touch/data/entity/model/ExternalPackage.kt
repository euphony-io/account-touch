package com.euphony.project.account_touch.data.entity.model
import com.euphony.project.account_touch.R


enum class ExternalPackage(val packageId: Int){
    TOSS(R.string.toss_package),
    KAKAOPAY(R.string.kakaopay_package),

    KAKAOBANK(R.string.kakaobank_package),
    KOOKMIN(R.string.kookmin_package),
    KBANK(R.string.kbank_package),
    WOORI(R.string.woori_package),
    NH(R.string.nh_package),
    IBK(R.string.ibk_package),
    SHINHAN(R.string.shinhan_package),
    KEB(R.string.hanabank_package),
    SINHYEOB(R.string.sinhyeob_package),
}