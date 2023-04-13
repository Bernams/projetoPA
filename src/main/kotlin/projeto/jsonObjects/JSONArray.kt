package projeto.jsonObjects

import JSONVisitor

class JSONArray : JSONElement {
    private val elements = mutableListOf<JSONElement>()

    fun add(element: JSONElement) {
        elements.add(element)
    }

    fun get(index: Int): JSONElement? {
        return if (index in elements.indices) elements[index] else null
    }

    fun size(): Int {
        return elements.size
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
        elements.forEach { it.accept(visitor) }
    }

    override fun toJSONString(): String {
        val jsonString = elements.joinToString(separator = ", ") { it.toJSONString() }
        return "[$jsonString]"
    }
}
