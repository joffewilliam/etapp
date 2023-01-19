package com.example.myapplication

import java.time.LocalTime


object GFG {
    @JvmStatic
    fun main(args: Array<String>) {

        // create a LocalTime object
        val time = LocalTime.parse("01:00:01")

        // print LocalTime
        println(
            "LocalTime before addition: "
                    + time
        )

        // add -600 Minutes using plusMinutes()
        val value = time.plusMinutes(-600)

        // print result
        println(
            ("LocalTime after addition: "
                    + value)
        )
    }
}