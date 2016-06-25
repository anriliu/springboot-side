package com.github.yingzhuo.springboot.side.kotlin.profile

import com.github.yingzhuo.springboot.side.util.SpringUtils

inline fun ifProfileActived(profile: String, lambda: () -> Unit): Unit {
    if (SpringUtils.isProfileActived(profile)) {
        lambda()
    }
}

fun activedProfiles(): List<String> = SpringUtils.getActivedProfiles()

fun isProfileActived(profile: String): Boolean = SpringUtils.isProfileActived(profile)
