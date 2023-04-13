package projeto.visitor
import JSONVisitor
import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONObject
import projeto.jsonObjects.JSONValue

class validatePropertyValue<T>(private val property : String, private val expectedProperty : Class<T>) : JSONVisitor {

    var valid: Boolean = true

    override fun visit(jsonObject: JSONObject){
       // if(jsonObject.get(property) is JSONValue){
        //    if(expectedProperty.isInstance((jsonObject.get(property) as JSONValue).getValue())) {
         //       valid = true
         ///   }
       // }
        jsonObject.get(property)?.let { value ->
            valid = expectedProperty.isInstance((value as JSONValue).value)
            println(valid)
        }
    }

    override fun visit(jsonArray: JSONArray) {

    }

    override fun visit(jsonValue: JSONValue) {

    }
}