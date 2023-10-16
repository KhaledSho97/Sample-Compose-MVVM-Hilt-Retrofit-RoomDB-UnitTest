package com.khaled_sho.testmedicalapp.core.util

fun String?.ifEmptyOrNull(defaultValue: String, string: ((String) -> Unit)) {
    if (this.isNullOrEmpty()) {
        string.invoke(defaultValue)
    } else {
        string.invoke(this)
    }
}

fun String?.ifEmptyOrNull(defaultValue: String): String {
    return if (this.isNullOrEmpty()) {
        defaultValue
    } else {
        this
    }
}