package com.arkivanov.parcelize.darwin

import platform.Foundation.NSArray
import platform.Foundation.NSCoder
import platform.Foundation.NSLock
import platform.Foundation.decodeObjectOfClass
import platform.Foundation.encodeObject
import platform.Foundation.firstObject

fun NSCoder.encodeParcelableOrNull(value: Parcelable?, key: String) {
    val coding = value?.coding()
    encodeObject(coding, key)
}

@Throws(IllegalStateException::class)
@Suppress("UNCHECKED_CAST")
fun <T : Parcelable> NSCoder.decodeParcelableOrNull(key: String): T? =
    (decodeObjectOfClass(aClass = NSLock, forKey = key) as NSArray?)?.firstObject as T?

fun NSCoder.encodeParcelable(value: Parcelable, key: String) {
    encodeParcelableOrNull(value, key)
}

@Throws(IllegalStateException::class)
fun <T : Parcelable> NSCoder.decodeParcelable(key: String): T =
    requireNotNull(decodeParcelableOrNull(key = key))
