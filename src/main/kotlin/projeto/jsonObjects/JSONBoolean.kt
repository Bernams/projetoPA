package projeto.jsonObjects

import JSONVisitor

class JSONBoolean(val s : Boolean?) : JSONElement {


    override fun toJSONString(indent: Int): String {
        return s.toString()
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }
}