package me.daqiang


class LibProvider(name: String) {

    var firstProperty = "First property : $name".also(::println)

    init {

        "test alse function".also {
            println(it)
        }
        print("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}"

    init {
        println("Second initializer block that prints ${name.length}")
    }

}