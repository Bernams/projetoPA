import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONObject
import projeto.jsonObjects.JSONValue

interface JSONVisitor {
    fun visit(jsonObject: JSONObject)
    fun visit(jsonArray: JSONArray)
    fun visit(jsonValue: JSONValue)
}