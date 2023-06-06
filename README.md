Projeto desenvolvido por:
  Miguel d'Aguiar 92448
  Bernardo Santos 92710

## Definition of JSON elements

All the JSONObjects implement the JSONElement interface. Every class that implements the JSONElement should have the following methods defined:

- toJSONString(indent : Int = 0) : String
  - This method can accept an indent value that is used for the indentation in the JSON itself. It has the default value of no-indent (0).
- accept(visitor : JSONVisitor)
  - This method is used for the visitors.

### JSONString

JSON element that represents **Strings**

```kotlin
val jsonString1 = JSONString("Projeto PA")
val jsonString2 = JSONString("Kotlin")
val jsonString3 = JSONString("ABC")
```

### JSONNumber

JSON element that represents **Numbers**

```kotlin
val jsonNumber1 = JSONNumber(34)
val jsonNumber2 = JSONString(3.14)
val jsonNumber3 = JSONString(2023)
```

### JSONBoolean

JSON element that represents ****************Booleans****************

```kotlin
val jsonBoolean1 = JSONBoolean(true)
val jsonBoolean2 = JSONBoolean(false)
```

### JSONNull

JSON element that represents **********Nulls**********

```kotlin
val jsonNull = JSONNull()
```

### JSONArray

JSON element that represents **********Arrays**********

```kotlin
val jsonArray = JSONArray()
val jsonString = JSONString("Projeto PA")
val jsonNumber = JSONNumber(2023)
jsonArray.add(jsonString)
jsonArray.add(jsonNumber)
```

On the JSON Array represented earlier you can also perform some operations

### Size()

```kotlin
jsonArray.size()
// returns 2
```

### Get(index) and GetAll()

```kotlin
jsonArray.get(0)
// returns JSONString("Projeto PA")

jsonArray.getAll()
// returns List("Projeto PA", 2023)
```

### RemoveAt(index) and Remove(JSONElement)

```kotlin
jsonArray.removeAt(0) 
jsonArray.remove(jsonString)
// Both operations remove the element at index 0
```

### JSONObject

JSON element that represents **************Objects**************

```kotlin
val jsonObject = JSONObject()
val jsonString = JSONString("Projeto PA")
val jsonNumber = JSONNumber(2023)
jsonObject.put("title", jsonString)
jsonObject.put("year", jsonNumber)
```

On the JSON Object represented above you can also perform some operations

### Size()

```kotlin
jsonObject.size()
//returns 2
```

### Get(key)

```kotlin
jsonObject.get("title")
// returns JSONString("Projeto PA")
```

### GetProperties()

```kotlin
jsonObject.getProperties()
// returns List("title", "year")
```

### Remove(key)

```kotlin
jsonObject.remove("title")
// removes the "title" entry
```

## Annotations

You may choose to use the following annotations

| Annotation       | Description                                                                                       |
|------------------|---------------------------------------------------------------------------------------------------|
| @JSONExclude     | This annotation will exclude a specific attribute from the Reflector.reflect()                    |
| @JSONForceString | This annotation will force a specific attribute to be a String from the Reflector.reflect()       |
| @JSONCustomKey   | This annotation will create a custom attribute’s key instead of the one defined when instanciated |

```kotlin
data class TestData(val a: String,@JSONCustomKey("TESTE") val b: Int, val c: Boolean)
val obj = TestData("pa", 92448, true)
val result = reflector.reflect(obj)
//returns A JSONObject with keys "a", "TESTE" and "c"
```

## Validations

There are filters and validations that you can choose

### Find objects with key

With this validation you can query the top level object and get all the objects with the key or keys given.

```kotlin
val rootObject = JSONObject()
// ... define the object here

val queryVisitor = FindObjectsWithKey(listOf("title", "year"))
jsonObject.accept(queryVisitor)

queryVisitor.getFound()
queryVisitor.getFound().size // returns the number of found values
```

To display the objects found with both the keys “title” and “year” inside of it you must use the method getFound(). A list of objects will be returned and so normal list operations can be used (e.g. ****size****)

### Find values with key

This validation is similar to the one described above but intead of returning the object that contains the list of keys it returns the values as a String that have that key.

```kotlin
val rootObject = JSONObject()
// ... define the object here

val queryVisitor = FindValuesWithKey("title")
jsonObject.accept(queryVisitor)

queryVisitor.getFound() // returns List("Projeto PA")
```

To display the values found with the key “title” you must use the method getFound(). A list of objects will be returned and so normal list operations can be used (e.g. ****size****)

### Validate inscritos property

This validation applies to the JSONArray of key “inscritos” and validates whether the array structure is the same for all the elements of the array and returns a boolean value.

```kotlin
val rootObject = JSONObject()
// ... define the object here

val queryVisitor = ValidateInscritosProperty()
jsonObject.accept(queryVisitor)

queryVisitor.getValidation() // returns True/False
```

### Validate numero property

This validation approves whether the “number” property is in fact a number of type Int and returns a boolean value.

```kotlin
val rootObject = JSONObject()
// ... define the object here

val queryVisitor = ValidateNumeroProperty()
jsonObject.accept(queryVisitor)

queryVisitor.getValidation() // returns True/False
```

## JSONReflector

You can infer the JSONElement from every type using the JSONReflector class. For example, you can input any Iterable element, and it will make the adaptation for JSONArray.

```kotlin
val list = listOf("Projeto PA", 2023)
val result = reflector.reflect(list)
// returns a JSONArray(JSONString("Projeto PA", JSONNumber(2023))
```

### Usage Example

You can use the Reflector class to implement your own data classes directly and automatically return the JSON text or the JSONElements.

```kotlin
// example dataclass
data class ExampleDataClass(val nome: String, val numero: Int, val internacional: Boolean)
val obj = ExampleDataClass("Miguel Aguiar", 92448, false)

// instanciate reflector
val reflector = JSONReflector()

// call reflect method that will return the JSONElements
val result = reflector.reflect(obj)

// to get the json text you can call this method
result.toJSONString()
```