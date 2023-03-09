package com.example.wanandroid_k_m_j.kts

import java.text.DecimalFormat
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun String?.isNull(): Boolean {
    contract {
        returns(false) implies (this@isNull != null)
    }
    return this.isNullOrBlank() || "null" == this || "" == this
}

@OptIn(ExperimentalContracts::class)
fun String?.notNull(): Boolean {
    contract {
        returns(true) implies (this@notNull != null)
    }
    return !this.isNull()
}

@OptIn(ExperimentalContracts::class)
fun String?.getValue(default: String): String {
    return if (isNull()) {
        default
    } else {
        this
    }
}

fun Double.getPrice(): String {
    if (this.compareTo(this.toInt()) != 0)
        return this.toString()
    else {
        return this.toInt().toString()
    }
}

fun Float.getPrice(): String {
    if (this.compareTo(this.toInt()) != 0) {
        val df = DecimalFormat("0.00")
        var text = df.format(this)
        if (text.endsWith(".00")) {
            text = text.replace(".00", "")
        }
        if (text.endsWith(".0")) {
            text = text.replace(".0", "")
        }
        return text
    } else {
        return this.toInt().toString()
    }
}

fun Boolean.not(): Boolean = !this

fun <T> Any.to(): T {
    return this as T
}
