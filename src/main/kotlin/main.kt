fun main(args: Array<String>) {
    runPropertyDelegatesExample()
}

private fun runPropertyDelegatesExample() {
    val example = Example()
    println(example.l)
    println(example.l)
    println(example.l)
    println(example.p1)
    println(example.p2)


    val user = User(mutableMapOf(
            "name" to "testUser"
    ))
    println(user.name)
    user.name = "testUserModified"
    println(user.name)
}

