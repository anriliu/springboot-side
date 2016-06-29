package com.github.yingzhuo.springboot.side.kotlin.profile

import com.github.yingzhuo.springboot.side.util.SpringUtils

inline fun ifProfileActived(profile: String, lambda: () -> Unit): Unit {
    if (SpringUtils.acceptsProfiles(profile)) {
        lambda()
    }
}

inline fun ifAcceptsProfiles(vararg profiles: String, lambda: () -> Unit): Unit {
    if (SpringUtils.acceptsProfiles(*profiles)) {
        lambda()
    }
}

fun activedProfiles(): List<String> = SpringUtils.getActivedProfiles()

fun isProfileActived(profile: String): Boolean = SpringUtils.acceptsProfiles(profile)
