package projeto.jsonObjects

import JSONVisitor

class JSONValue(s: String) : JSONElement {

    val value: Any? = when {
        s == "null" -> null
        s == "true" -> true
        s == "false" -> false
        s.matches(Regex("-?\\d+")) -> s.toInt()
        s.matches(Regex("-?\\d+\\.\\d+")) -> s.toDouble()
        else -> s
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }

    override fun toJSONString() : String {
        return value.toString()
    }
}
