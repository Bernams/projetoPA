import projeto.jsonObjects.*

interface JSONVisitor {
    fun visit(jsonObject: JSONObject)
    fun visit(jsonArray: JSONArray)
    fun visit(jsonString: JSONString)
    fun visit(jsonBoolean: JSONBoolean)
    fun visit(jsonNumber: JSONNumber)
}