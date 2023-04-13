import projeto.jsonObjects.JSONElement
import java.io.File
import java.io.IOException

class JSONFileCreator(private val outputPath: String) {

    @Throws(IOException::class)
    fun writeToFile(jsonElement: JSONElement) {
        val jsonString = jsonElement.toJSONString()
        try {
            val file = File(outputPath)
            file.writeText(jsonString)
        } catch (e: IOException) {
            throw IOException("Error writing JSON to file: ${e.message}", e)
        }
    }
}