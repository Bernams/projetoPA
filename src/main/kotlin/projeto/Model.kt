package projeto

import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONElement
import projeto.jsonObjects.JSONObject

class Model : Observable {
    override val observers = arrayListOf<Observer>()

    var jsonModel = JSONObject()

    override fun add(observer: Observer) {
        observers.add(observer)
    }

    override fun remove(observer: Observer) {
        observers.remove(observer)
    }

    override fun sendUpdateEvent() {
        observers.forEach{ it.update() }
    }

    fun printJSON() : String {
        return jsonModel.toJSONString()
    }

    fun addNestedObject(context: JSONObject, key: String, obj: JSONObject) {
        var newLabel = key
        var counter = 1
        while(context.getProperties().contains(newLabel)){
            newLabel = key.plus("($counter)")
            counter++
        }
        context.put(newLabel, obj)
    }

    fun removeNestedObject(context: JSONObject) {
        val propertiesToRemove = mutableListOf<String>()

        for (property in context.getProperties()) {
            val value = context.get(property)
            if (value is JSONObject) {
                removeNestedObject(value)
            }
            propertiesToRemove.add(property)
        }

        for (property in propertiesToRemove) {
            context.remove(property)
        }
    }

    fun addNestedArray(context: JSONObject, key: String, arr: JSONArray) {
        var newLabel = key
        var counter = 1
        while(context.getProperties().contains(newLabel)){
            newLabel = key.plus("($counter)")
            counter++
        }
        context.put(newLabel, arr)
    }

    fun addNestedValue(context: JSONObject, key: String, value: JSONElement) {
        context.put(key, value)
    }

    fun addArrayNestedObject(context: JSONArray, jsonObject: JSONObject) {
        context.add(jsonObject)
    }

    fun addArrayNestedArray(context: JSONArray, jsonObject: JSONArray) {
        context.add(jsonObject)
    }

    fun addArrayValue(context: JSONArray, value: JSONElement) {
        context.add(value)
    }

}