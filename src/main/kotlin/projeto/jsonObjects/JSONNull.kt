package projeto.jsonObjects

import JSONVisitor

class JSONNull() : JSONElement {

    override fun toJSONString(indent: Int): String {
        return "null"
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }
}