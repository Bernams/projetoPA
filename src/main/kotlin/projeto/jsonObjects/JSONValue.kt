package projeto.jsonObjects

import JSONVisitor
import java.lang.IllegalArgumentException

class JSONValue(s: String) : JSONElement {

    val value: Any? = when {
        s == "null" -> null
        s == "true" -> true
        s == "false" -> false
        s.matches(Regex("-?\\d+")) -> s.toInt()
        s.matches(Regex("-?\\d+\\.\\d+")) -> s.toDouble()
        s is String -> "\$value\""
        else -> throw IllegalArgumentException("The provided argument is not valid.")
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }

    override fun toJSONString() : String {
        return value.toString()
    }
}
