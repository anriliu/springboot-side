package com.github.yingzhuo.springboot.side.kotlin.profile

import com.github.yingzhuo.springboot.side.profile.ProfileUtils

inline fun inProfile(profile: String, lambda: () -> Unit): Unit {
    if (profile in ProfileUtils.getActiveProfilesAsSet()) lambda()
}

fun activeProfiles(): List<String> =
        ProfileUtils.getActiveProfiles()

fun isInProfile(profile: String): Boolean =
        profile in activeProfiles()
