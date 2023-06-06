package projeto.jsonObjects

import JSONVisitor

class JSONBoolean(val bool : Boolean) : JSONElement {

    override fun toJSONString(indent: Int): String {
        return bool.toString()
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }
}