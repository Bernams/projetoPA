package projeto

import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONElement
import projeto.jsonObjects.JSONObject
import projeto.ui.Command
import java.util.*

class Model : Observable {

    override val observers = arrayListOf<Observer>()
    var jsonModel = JSONObject()
    private val commandStack = Stack<Command>()

    fun executeCommand(command: Command) {
        command.execute()
        commandStack.push(command)
        sendUpdateEvent()
    }

    fun undo() {
        if (commandStack.isNotEmpty()) {
            val command = commandStack.pop()
            command.undo()
            sendUpdateEvent()
        }
    }

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

    fun removeNestedObjectWithKey(context: JSONObject, key : String) {
        context.remove(key)
    }

    fun removeNestedArray(context: JSONArray) {
        val propertiesToRemove = mutableListOf<Int>()

        for (i in 0 until context.size()) {
            val value = context.get(i)
            if (value is JSONObject) {
                removeNestedObject(value)
            } else if (value is JSONArray)
                removeNestedArray(value)

        propertiesToRemove.add(i)
        }

        for (property in propertiesToRemove) {
            context.removeAt(property)
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

    fun addNestedArrayValue(context: JSONArray, value: JSONElement) {
        context.add(value)
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