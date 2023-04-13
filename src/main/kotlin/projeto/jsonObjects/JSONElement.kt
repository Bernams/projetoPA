package projeto.jsonObjects

import JSONVisitor

interface JSONElement {
    fun toJSONString(indent: Int = 0) : String
    fun accept(visitor: JSONVisitor)
}