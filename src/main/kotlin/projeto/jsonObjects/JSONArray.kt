package projeto.jsonObjects

import JSONVisitor
import projeto.Observable
import projeto.Observer

class JSONArray : JSONElement, Observable {
    private val elements = mutableListOf<JSONElement>()

    override val observers = arrayListOf<Observer>()

    fun add(element: JSONElement) {
        elements.add(element)
        sendUpdateEvent()
    }

    fun remove(element: JSONElement) {
        elements.remove(element)
        sendUpdateEvent()
    }

    fun removeAt(index : Int) {
        elements.removeAt(index)
    }

    fun get(index: Int): JSONElement? {
        return if (index in elements.indices) elements[index] else null
    }

    fun getAll():  List<JSONElement>{
        return elements
    }

    fun size(): Int {
        return elements.size
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
        elements.forEach { it.accept(visitor) }
    }

     override fun toJSONString(indent: Int): String {
        if (elements.isEmpty()) return "[]"

        val indentString = " ".repeat(indent)
        val childIndentString = " ".repeat(indent + 2)
        val jsonString = elements.joinToString(separator = ",\n$childIndentString") {
            it.toJSONString(indent + 2)
        }
        return "[\n$childIndentString$jsonString\n$indentString]"
    }
}
