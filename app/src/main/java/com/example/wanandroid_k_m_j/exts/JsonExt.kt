package com.example.wanandroid_k_m_j.exts

import com.google.gson.JsonObject

/**
 * 任意对象转jsonString
 * 备注：JSONObject禁止直接使用
 * @receiver Collection<Any>
 * @return String?
 */
fun Any.toJsonString(): String? = JsonUtil.obj2JsonString(this)


/**
 * 任意对象装JsonObj
 * @receiver Any
 * @return JsonObject?
 */
fun Any.toJsonObj(): JsonObject? = JsonUtil.string2JsonObj(this?.toJsonString())


/**
 * 当前json 字符串中是否含有有个字段的值
 * @receiver String?
 * @return Boolean
 */
fun String?.hasJsonData(key: String?): Boolean = JsonUtil.hasJsonData(this, key)


/**
 * jsonString 转对象
 * @receiver String?
 * @return T
 */
inline fun <reified T> String?.fromJsonObject(): T? = JsonUtil.string2Obj(this)


/**
 * json string转数组
 * @receiver String?
 * @param clazz Class<T>
 * @return Collection<T>?
 */
inline fun <reified T> String?.fromJsonList(): Collection<T>? =
    JsonUtil.string2List(this, JsonUtil.genericType<Collection<T>>())


/**
 * jsonString 转 Map
 * @receiver String?
 * @param type Type
 * @return Map<K, V>?
 */
inline fun <reified K, reified V> String?.fromJsonMap(): Map<K, V>? =
    JsonUtil.string2Map(this, JsonUtil.genericType<Map<K, V>>())


/**
 * jsonString 根据关键字获取数组
 * @receiver String?
 * @param key String?
 * @param clazz Class<T>
 * @return Collection<T>
 */
inline fun <reified T> String?.getJsonList(
    key: String?
): Collection<T>? = JsonUtil.getListByKey<T>(this, key, JsonUtil.genericType<Collection<T>>())

/**
 * jsonString 获取int类型
 * @receiver String?
 * @param key String?
 * @return Int?
 */
fun String?.getJsonInt(key: String?): Int? = JsonUtil.getJsonInt(this, key)


/**
 * jsonString 获取Boolean类型
 * @receiver String?
 * @param key String?
 * @return Boolean
 */
fun String?.getJsonBoolean(key: String?): Boolean? = JsonUtil.getJsonBoolean(this, key)


/**
 * jsonString 获取String类型
 * @receiver String?
 * @param key String?
 * @return Int?
 */
fun String?.getJsonString(key: String?): String? = JsonUtil.getJsonString(this, key)


/**
 * jsonString 获取int类型
 * @receiver String?
 * @param key String?
 * @return Int?
 */
fun String?.getJsonDouble(key: String?): Double? = JsonUtil.getJsonDouble(this, key)




