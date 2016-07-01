package com.github.yingzhuo.springboot.side.kotlin.springmvc

import org.springframework.ui.ModelMap
import org.springframework.web.servlet.ModelAndView

infix fun String.and(model: Map<String, Any>?): ModelAndView =
        ModelAndView(this, model ?: mapOf<String, Any>())

infix fun String.and(modelMap: ModelMap?): ModelAndView =
        ModelAndView(this, modelMap ?: ModelMap())

infix fun String.and(model: Pair<String, Any>): ModelAndView =
        ModelAndView(this, model.first, model.second)

val ModelAndView.isNotEmpty: Boolean get() = !isEmpty

val ModelAndView.size: Int get() = this.modelMap.size

