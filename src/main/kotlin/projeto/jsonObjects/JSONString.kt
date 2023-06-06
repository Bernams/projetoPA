package projeto.jsonObjects

import JSONVisitor

class JSONString(val str: String?) : JSONElement {



    override fun toJSONString(indent: Int): String {
        return '"' + str.toString()+ '"'
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }
}