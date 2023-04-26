package projeto

import projeto.jsonObjects.*
import kotlin.reflect.full.*

class JSONReflector {

    fun reflect(obj : Any?) : JSONElement {
        if( obj == null) return JSONString(null)
        when (obj) {
            is String -> return JSONString(obj)
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
                obj::class.memberProperties.forEach { prop ->
                    val name = prop.name
                    val value = prop.getter.call(obj)
                    jsonObject.put(name, reflect(value))
                }
                return jsonObject
            }
        }
    }
}

/**
fun createList(): List<Any?> {
    return mutableListOf(1, 2, 3, 4, true, null, "asd", createMap())
}

fun createMap(): Map<String, Any?> {
    return mapOf("test" to "value", "number" to 92448)
}

fun main() {
    val list = createList()

    val reflex = JSONReflector()
    val jsonObject = reflex.reflect(list)
    println(jsonObject.toJSONString())
}
 **/