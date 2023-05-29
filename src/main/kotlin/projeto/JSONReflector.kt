package projeto

import JSONCustomKey
import JSONExclude
import JSONForceString
import projeto.jsonObjects.*
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

class JSONReflector {

    fun reflect(obj : Any?) : JSONElement {
        if(obj == null) return JSONNull()
        return when (obj) {
            is String, is Char -> JSONString(obj.toString())
            is Number -> JSONNumber(obj)
            is Boolean -> JSONBoolean(obj)
            is Enum<*> -> JSONString(obj.name)
            is Iterable<*> -> {
                val jsonArray = JSONArray()
                obj.forEach{jsonArray.add(reflect(it))}
                jsonArray
            }
            is Map<*, *> -> {
                val jsonObject = JSONObject()
                obj.forEach { (k, v) -> jsonObject.put(k.toString(), reflect(v)) }
                jsonObject
            }
            else -> {
                val jsonObject = JSONObject()
                for (prop in obj::class.memberProperties) {
                    if (prop.hasAnnotation<JSONExclude>()) continue
                    val name =
                        if (prop.hasAnnotation<JSONCustomKey>()) prop.findAnnotation<JSONCustomKey>()!!.identifier
                        else prop.name
                    val value =
                        if (prop.hasAnnotation<JSONForceString>()) prop.getter.call(obj).toString()
                        else prop.getter.call(obj)
                    jsonObject.put(name, reflect(value))
                }
                jsonObject
            }
        }
    }
}