import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Example {
    var p1: String by provideDelegateForP()
    var p2: String by DelegateExample()
    val l: String by lazy {
        println("computing l")
        "valueOfL"
    }
}


fun provideDelegateForP(): ReadWriteProperty<Example, String> {
    return DelegateExample()
}

class DelegateExample : ReadWriteProperty<Example, String> {

    var actualP: String = "initialValue"

    override fun setValue(thisRef: Example, property: KProperty<*>, value: String) {
        println("delegated setter called for $thisRef object for property $property with $value")
        actualP = value
    }

    override fun getValue(thisRef: Example, property: KProperty<*>): String {
        println("delegated getter called for $thisRef object for property $property")
        return actualP
    }

    operator fun provideDelegate(thisRef: Example, property: KProperty<*>): DelegateExample {
        println("provideDelegate method called for ${property.name}")
        return DelegateExample()
    }

}

class User(map: MutableMap<String, String>) {
    var name: String by map
}

