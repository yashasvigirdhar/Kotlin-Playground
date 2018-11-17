fun delegationInClasses() {
    Derived(BaseImpl(1)).printMyString()
    Derived_OverrideFunction(BaseImpl(2)).printMyString()
    Derived_OverrideProp(BaseImpl(3)).printMyString()
    Derived_CallSuperToo(BaseImpl(4)).printMyString()
}

interface Base {
    val prop: Int
    fun printMyString()
}

class BaseImpl(val x: Int) : Base {
    override val prop = 1
    override fun printMyString() = println("from base: $x and $prop")
}

// Below line throws compile time error: Only interfaces can be delegated to.
// explanation: https://stackoverflow.com/questions/46387525/why-only-interfaces-can-be-delegated-to-in-kotlin
// class actual(b: BaseImpl) : BaseImpl by b

class Derived(b: Base) : Base by b

class Derived_OverrideFunction(b: BaseImpl) : Base by b {
    override fun printMyString() {
        println("from derived")
        // can't access b here. need to declare it as a property. See Derived_CallSuperToo class below
    }
}

class Derived_OverrideProp(b: BaseImpl) : Base by b {
    // this prop is not accessible in the class to which we are delegating
    override val prop: Int
        get() = 3
}

class Derived_CallSuperToo(val b: BaseImpl) : Base by b {
    override fun printMyString() {
        println("from derived, calling base now")
        b.printMyString()
    }
}

// Below code throws compile error: Delegation without primary constructor is not supported
//class Derived: Base by b {
//    constructor(b: Base) {
//
//    }
//}


/**
 * Note: This file's generated Java code is quite interesting!
 * All the derived classes implement the Base interface as well as, initialize a property of type
 * BaseImpl and delegate to the property. Kind of decorator pattern
 */
//
//