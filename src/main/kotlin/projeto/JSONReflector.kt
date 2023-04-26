package projeto

import JSONExclude
import projeto.jsonObjects.*
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

class JSONReflector {

    fun reflect(obj : Any?) : JSONElement {
        when (obj) {
            null -> return JSONString(null)
            is String, is Char -> return JSONString(obj.toString())
            is Number -> return JSONNumber(obj)
            is Boolean -> return JSONBoolean(obj)
            is Enum<*> -> return JSONString(obj.name)
            is Iterable<*> -> {
                val jsonArray = JSONArray()
                obj.forEach{jsonArray.add(reflect(it))}
                return jsonArray
            }
            is Map<*, *> -> {
                val jsonObject = JSONObject()
                obj.forEach { (k, v) -> jsonObject.put(k.toString(), reflect(v)) }
                return jsonObject
            }
            else -> {
                val jsonObject = JSONObject()
                for (prop in obj::class.memberProperties) {
                    if (prop.hasAnnotation<JSONExclude>()) {
                        continue
                    }
                    val name = prop.name
                    val value = prop.getter.call(obj)
                    jsonObject.put(name, reflect(value))
                }
                return jsonObject
            }
        }
    }
}