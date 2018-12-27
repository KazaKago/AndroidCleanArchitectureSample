package com.kazakago.cleanarchitecture.presentation.global.extension

interface StringKey

fun <T> T.value(): String where T : Enum<*>, T : StringKey {
    return name
}

fun <T> Array<T>.resolve(value: String): T where T : Enum<*>, T : StringKey {
    return first { it.value() == value }
}

fun <T> Array<T>.resolveOrNull(value: String): T? where T : Enum<*>, T : StringKey {
    return firstOrNull { it.value() == value }
}

fun <T> Array<T>.resolve(value: String, defaultValue: T): T where T : Enum<*>, T : StringKey {
    return resolveOrNull(value) ?: defaultValue
}
