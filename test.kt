fun main(args: Array<String>) {

    var wat = "123456789"

    for (i in 0..wat.length-1) {
        wat = wat.removeRange(2, 3)
        break
    }
    println(wat)
}