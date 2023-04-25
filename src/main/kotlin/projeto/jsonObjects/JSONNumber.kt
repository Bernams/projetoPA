package projeto.jsonObjects

import JSONVisitor

class JSONNumber(val s: Number?) : JSONElement {


    override fun toJSONString(indent: Int): String {
        return s.toString()
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }
}