package projeto.jsonObjects

import JSONVisitor

interface JSONElement {
    fun toJSONString() : String
    fun accept(visitor: JSONVisitor)
}