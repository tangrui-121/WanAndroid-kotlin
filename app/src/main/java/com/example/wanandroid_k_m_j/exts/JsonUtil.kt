package com.example.wanandroid_k_m_j.exts

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.lang.reflect.Type
import kotlin.jvm.Throws

/**
 * Json数据解析工具类
 */
object JsonUtil {

    val safeGson: Gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(
                Long::class.javaPrimitiveType,
                NumberTypeAdapter(NumberType.LONG)
            ) // 自行处理long 类型数据解析
            .registerTypeAdapter(Long::class.java, NumberTypeAdapter(NumberType.LONG))
            .registerTypeAdapter(
                Float::class.javaPrimitiveType,
                NumberTypeAdapter(NumberType.FLOAT)
            ) // float 类型数据解析
            .registerTypeAdapter(Float::class.java, NumberTypeAdapter(NumberType.FLOAT))
            .registerTypeAdapter(
                Double::class.javaPrimitiveType,
                NumberTypeAdapter(NumberType.DOUBLE)
            ) // 自行处理double 类型数据解析
            .registerTypeAdapter(Double::class.java, NumberTypeAdapter(NumberType.DOUBLE))
            .registerTypeAdapter(
                Int::class.javaPrimitiveType,
                NumberTypeAdapter(NumberType.INT)
            ) // 自行处理int 类型数据解析
            .registerTypeAdapter(Int::class.java, NumberTypeAdapter(NumberType.INT)).create()
    }

    /**
     * 任意对象转Json字符串
     * @param obj Any
     * @return String?
     */
    @JvmStatic
    fun obj2JsonString(obj: Any?): String? {
        return safeGson.toJson(obj)
    }

    /**
     * jsonstring 中是否包含key 对应的数据
     * @param data String?
     * @param key String?
     * @return Boolean
     */
    @JvmStatic
    fun hasJsonData(data: String?, key: String?): Boolean {
        if (!isValid(data) || !isValid(key)) {
            return false
        }
        val obj = string2JsonObj(data)
        return obj?.get(key) != null
    }

    /**
     * jsonString 转 对象
     * @param data String?
     * @param clazz Class<T>
     * @return T?
     */
    @JvmStatic
    inline fun <reified T> string2Obj(data: String?): T? {
        if (!isValid(data)) {
            return null
        }
        try {
            return safeGson.fromJson(data, T::class.java)
        } catch (e: Exception) {

        }
        return null
    }

    /**
     * jsonString 转 List
     * @param data String?
     * @param type Type
     * @return Collection<T>?
     */
    @JvmStatic
    inline fun <reified T> string2List(data: String?, type: Type): Collection<T>? {
        if (!isValid(data)) {
            return null
        }
        try {
            return safeGson.fromJson(data, type)
        } catch (e: Exception) {

        }
        return null
    }

    /**
     * jsonString 转map
     * @param data String?
     * @param type Type
     * @return Map<K,V>
     */
    @JvmStatic
    inline fun <reified K, reified V> string2Map(data: String?, type: Type): Map<K, V>? {
        if (!isValid(data)) {
            return null
        }
        try {
            return safeGson.fromJson<Map<K, V>>(data, type)
        } catch (e: Exception) {
        }
        return null

    }

    /**
     * 获取对应的TokenType
     * @return (java.lang.reflect.Type..java.lang.reflect.Type?)
     */
    @JvmStatic
    inline fun <reified T> genericType(): Type = object : TypeToken<T>() {}.type


    /**
     * 获取jsonString 中的某个jsonArray 并解析为数组对象
     * @param data String?
     * @param key String?
     * @param type Type
     * @return Collection<T>?
     */
    @JvmStatic
    inline fun <reified T> getListByKey(data: String?, key: String?, type: Type): Collection<T>? {
        if (!isValid(data) || !isValid(key)) {
            return null
        }
        try {
            val obj = string2JsonObj(data)
            if (obj != null) {
                val jsonArray = obj.getAsJsonArray(key)
                if (jsonArray != null) {
                    return safeGson.fromJson<Collection<T>>(jsonArray, type)
                }
            }
        } catch (e: Exception) {
        }
        return null
    }

    /**
     * 仅根据关键字获取JSONArray对象
     * @param data String?
     * @param key String?
     * @return JSONArray
     */
    @JvmStatic
    fun getJsonArray(data: String?, key: String?): JsonArray? {
        if (!isValid(data) || !isValid(key)) {
            return null
        }
        try {
            val obj = string2JsonObj(data)
            if (obj != null) {
                return obj.getAsJsonArray(key)
            }
        } catch (e: Exception) {
        }
        return null
    }

    /**
     * string 转JsonObject
     * @param data String?
     * @return JSONObject?
     */
    @JvmStatic
    fun string2JsonObj(data: String?): JsonObject? {
        if (!isValid(data)) {
            return null
        }
        try {
            return JsonParser().parse(data).asJsonObject
        } catch (e: Exception) {

        }
        return null
    }

    /**
     * jsonString 获取int类型
     * @receiver String?
     * @param key String?
     * @return Int?
     */
    fun getJsonInt(data: String?, key: String?): Int? {
        if (!isValid(data) || !isValid(key)) {
            return null
        }
        try {
            val jsonObject: JsonObject? = string2JsonObj(data)
            return jsonObject?.get(key)?.asInt
        } catch (e: java.lang.Exception) {
        }
        return null

    }

    /**
     * jsonString 获取Boolean类型
     * @receiver String?
     * @param key String?
     * @return Boolean
     */
    fun getJsonBoolean(data: String?, key: String?): Boolean? {
        if (!isValid(data) || !isValid(key)) {
            return null
        }
        try {
            val jsonObject: JsonObject? = string2JsonObj(data)
            return jsonObject?.get(key)?.asBoolean
        } catch (e: java.lang.Exception) {
        }
        return null
    }

    /**
     * jsonString 获取String类型
     * @receiver String?
     * @param key String?
     * @return Int?
     */
    fun getJsonString(data: String?, key: String?): String? {
        if (!isValid(data) || !isValid(key)) {
            return null
        }
        val jsonObject: JsonObject? = string2JsonObj(data)
        return jsonObject?.get(key)?.asString
    }

    /**
     * jsonString 获取Double类型
     * @receiver String?
     * @param key String?
     * @return Int?
     */
    fun getJsonDouble(data: String?, key: String?): Double? {
        if (!isValid(data) || !isValid(key)) {
            return null
        }
        val jsonObject: JsonObject? = string2JsonObj(data)
        return jsonObject?.get(key)?.asDouble
    }

    /**
     * 可能的数字类型
     */
    internal enum class NumberType {
        INT, LONG, FLOAT, DOUBLE
    }

    /**
     * 自定义数值类型json解析
     * 解决因 gson java.lang.NumberFormatException: empty String?，引起的整个数据bean解析失败
     */
    internal class NumberTypeAdapter(numberType: NumberType) :
        TypeAdapter<Number?>() {
        var numberType: NumberType = numberType

        @Throws(IOException::class)
        override fun write(
            out: JsonWriter,
            value: Number?
        ) {
            out.value(value)
        }

        override fun read(jsonReader: JsonReader): Number? {
            return getNumberByType(jsonReader, numberType)
        }
    }

    /**
     * 类型转换
     *
     * @param in
     * @param numberType
     * @return
     * @throws IOException
     */
    private fun getNumberByType(
        jsonReader: JsonReader,
        numberType: NumberType
    ): Number? {
        try {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull() // 使jsonReader读取指针走到下一个
                return null
            }
            val result = jsonReader.nextString() //读取值，并使jsonReader读取指针走到下一个
            return if ("" == result) {
                null
            } else when (numberType) {
                NumberType.LONG -> result.toLongOrNull()
                NumberType.DOUBLE -> result.toLongOrNull()
                NumberType.FLOAT -> result.toLongOrNull()
                else -> result.toIntOrNull()
            }
        } catch (e: Exception) {
        }
        return null
    }

    fun isValid(v: String??): Boolean {
        return !(v == null || v == "null") && !v.trim { it <= ' ' }.isEmpty()
    }
}