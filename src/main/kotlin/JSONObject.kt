class JSONObject : JSONElement{

    //create a map with key JsonValue pairs
    private val map = mutableMapOf<String, JSONValue>()


    //put method to add to map
    fun put(s: String, jsonValue: JSONValue) {
        map[s] = jsonValue
    }

    //size method to return size of map
    fun size(): Int {
        return map.size
    }

    //get method to return value of key
    fun get(s: String): Any? {
        return map[s]
    }

}
