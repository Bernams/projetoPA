package projeto.visitor

import JSONVisitor
import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONObject
import projeto.jsonObjects.JSONValue

class findObjectsWithProperty(private val properties: List<String>) : JSONVisitor {

    private val found = mutableListOf<JSONObject>()

    override fun visit(jsonObject: JSONObject) {
        jsonObject.getProperties().containsAll(properties).let {
            if (it) {
                found.add(jsonObject)
            }
        }
    }

    override fun visit(jsonArray: JSONArray) {

    }

    override fun visit(jsonValue: JSONValue) {

    }

}