class JSONValue(s: String) : JSONElement {

    val value: Any? = when {
        s == "null" -> null
        s == "true" -> true
        s == "false" -> false
        s.matches(Regex("-?\\d+")) -> s.toInt()
        s.matches(Regex("-?\\d+\\.\\d+")) -> s.toDouble()
        else -> s
    }
    override fun toJSONString() : String {
        return value.toString()
    }
}
