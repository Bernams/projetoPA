class JSONValue(s: String) {

    val value: Any? = when {
        s == "null" -> null
        s == "true" -> true
        s == "false" -> false
        s.matches(Regex("-?\\d+")) -> s.toInt()
        s.matches(Regex("-?\\d+\\.\\d+")) -> s.toDouble()
        else -> s
    }
}
