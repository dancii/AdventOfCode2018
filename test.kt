fun main(args: Array<String>) {
    var testmap: HashMap<Int, Int> = HashMap<Int, Int>()

    testmap = [0..60].map { it -> 0} 

    for ((key, value) in testmap) {
        println("Key: $key, Value: $value")
    }
}