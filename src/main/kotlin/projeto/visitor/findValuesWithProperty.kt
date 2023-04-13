package projeto.visitor

import JSONVisitor
import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONObject
import projeto.jsonObjects.JSONValue

class findValuesWithProperty(private val property: String): JSONVisitor {
    private val found = mutableListOf<String>()


    override fun visit(jsonObject: JSONObject) {
        jsonObject.get(property)?.let { found.add(it.toJSONString()) }
    }

    override fun visit(jsonArray: JSONArray) {

    }

    override fun visit(jsonValue: JSONValue) {

    }
}