internal fun runLambdaWithReceiverExample() {

    println("Regular lambda: ")
    var s = buildStringWithRegularLambda {
        it.append("inside lambda") // we have to use `it` to reference sb's functions
        it.append("\nending")
    }
    println(s)

    println()
    println("Lambda with receiver: ")

    s = buildStringWithLambdaWithReceiver {
        append("inside lambda")  // can directly use sb's functions
        append("\nending")
    }
    println(s)
}

fun buildStringWithRegularLambda(builderAction: (StringBuilder) -> Unit): String {
    val sb = StringBuilder("starting function\n")
    sb.append("!\n")
    builderAction(sb)
    return sb.toString()

}

fun buildStringWithLambdaWithReceiver(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder("starting function\n")
    sb.append("!\n")
    builderAction(sb)
    return sb.toString()

}

/**
 * This is just to demonstrate passing additional params to the lambda apart from the receiver
 */
fun buildStringWithLambdaWithReceiverWithAdditionalArgs(builderAction: StringBuilder.(Int) -> Unit): String {
    val sb = StringBuilder("starting function\n")
    sb.append("!\n")
    builderAction(sb, 0)
    return sb.toString()

}