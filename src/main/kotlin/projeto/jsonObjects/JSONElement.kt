package projeto.jsonObjects

import JSONVisitor
import projeto.Observable

interface JSONElement {
    fun toJSONString(indent: Int = 0) : String
    fun accept(visitor: JSONVisitor)
}