package com.euphony.project.account_touch.data.global.base.entity

import java.util.*

abstract class BaseEntity() {
    abstract val createDate: Date
    abstract val modifyDate: Date
}