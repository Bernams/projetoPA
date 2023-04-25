package projeto.jsonObjects

import JSONVisitor

class JSONString(val s: String?) : JSONElement {


    override fun toJSONString(indent: Int): String {
        return '"' + s.toString()+ '"'
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }
}