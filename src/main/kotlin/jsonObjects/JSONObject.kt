package jsonObjects

class JSONObject : JSONElement {

    //create a map with key JsonValue pairs
    private val map = mutableMapOf<String, JSONValue>()


    //put method to add to map
    fun put(key: String, jsonValue: JSONValue) {
        map[key] = jsonValue
    }

    //size method to return size of map
    fun size(): Int {
        return map.size
    }

    //get method to return value of key
    fun get(key: String): JSONValue? {
        return map[key]
    }

    override fun toJSONString(): String {
        val jsonString = map.entries.joinToString(separator = ", ") { (key, value) ->
            "\"$key\": ${value.toJSONString()}"
        }
        return "{$jsonString}"
        }
    }
