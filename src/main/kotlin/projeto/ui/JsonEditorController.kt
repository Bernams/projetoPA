package projeto.ui

import projeto.jsonObjects.JSONElement
import projeto.jsonObjects.JSONObject

class JsonEditorController(private var jsonModel: JSONObject) {

    // Method to handle adding a JSON element
    fun addJsonElement(key: String, element: JSONElement) {

        jsonModel.put(key, element)

        // Store the previous state somewhere
        // ...

        notifyObservers()
    }

    // Method to handle removing a JSON element
    fun removeJsonElement(key: String) {

        jsonModel.remove(key)

        notifyObservers()
    }

    // Notifying observers
    private fun notifyObservers() {
        // Implementation of notifying observers
    }

    // More methods to handle other user inputs...
}