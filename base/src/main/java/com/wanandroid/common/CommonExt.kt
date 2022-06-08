package com.wanandroid.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author TangRui
 * @description: 通用扩展
 * @date:2022/2/23 10:30
 */

/**
 * 转换String
 */
fun Any?.toJsonString(): String {
    return Gson().toJson(this)
}

/**
 * 转换String带格式化
 */
fun Any?.toJsonFormatterString(): String {
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    val je: JsonElement = JsonParser.parseString(toJsonString())
    return gson.toJson(je)
}

/**
 * json String格式化
 */
fun String?.parseString(): String {
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    val jsonElement = JsonParser.parseString(this)
    return gson.toJson(jsonElement)
}

class ParameterizedTypeImpl(private val clazz: Class<*>) : ParameterizedType {
    override fun getRawType(): Type = List::class.java
    override fun getOwnerType(): Type? = null
    override fun getActualTypeArguments(): Array<Type> = arrayOf(clazz)
}

/**
 * String转换List
 */
inline fun <reified T> String?.toJsonArray(): List<T>? {
    return Gson().fromJson<List<T>>(this, ParameterizedTypeImpl(T::class.java))
}

/**
 * 转换成对象
 */
inline fun <reified T> String.toJsonObject(): T {
    return Gson().fromJson(this, T::class.java)
}