package projeto.jsonObjects

import JSONVisitor

class JSONNumber(val num: Number?) : JSONElement {


    override fun toJSONString(indent: Int): String {
        return num.toString()
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }
}