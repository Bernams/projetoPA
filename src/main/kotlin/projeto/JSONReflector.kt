package projeto

import JSONCustomKey
import JSONExclude
import projeto.jsonObjects.*
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

class JSONReflector {

    fun reflect(obj : Any?) : JSONElement {
        if(obj == null) return JSONString(null)
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
                    val name =
                        if (prop.hasAnnotation<JSONCustomKey>()) prop.findAnnotation<JSONCustomKey>()!!.identifier
                        else prop.name
                    if (prop.hasAnnotation<JSONExclude>()) continue
                    jsonObject.put(name, reflect(prop.getter.call(obj)))
                }
                jsonObject
            }
        }
    }
}