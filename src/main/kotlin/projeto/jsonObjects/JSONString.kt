package projeto.jsonObjects

import JSONVisitor
import projeto.Observable

class JSONString(val str: String?) : JSONElement {



    override fun toJSONString(indent: Int): String {
        return '"' + str.toString()+ '"'
    }

    override fun accept(visitor: JSONVisitor) {
        visitor.visit(this)
    }
}