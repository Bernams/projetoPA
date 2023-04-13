package projeto.jsonObjects

import JSONVisitor

class JSONObject : JSONElement {

    //create a map with key JsonValue pairs
    private val map = mutableMapOf<String, JSONElement>()


    //put method to add to map
    fun put(key: String, jsonValue: JSONElement) {
        map[key] = jsonValue
    }

    //size method to return size of map
    fun size(): Int {
        return map.size
    }

    //get method to return value of key
    fun get(key: String): JSONElement? {
        return map[key]
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
        map.values.forEach { it.accept(visitor) }
    }
    override fun toJSONString(indent: Int): String {
        if (map.isEmpty()) return "{}"

        val indentString = " ".repeat(indent)
        val childIndentString = " ".repeat(indent + 2)
        val jsonString = map.entries.joinToString(separator = ",\n$childIndentString") { (key, value) ->
            "\"$key\": ${value.toJSONString(indent + 2)}"
        }
        return "{\n$childIndentString$jsonString\n$indentString}"
    }
}
