package com.example.app.data

class Color private constructor(
    var colorList: MutableList<String>,
){
    private fun randomColor(): String {
        val letters = "0123456789ABCDEF"
        var colorInit = "#"
        val colorLength = 6
        for(i in 0 until colorLength) {
            colorInit += letters[(0..15).random()]
        }
        return colorInit
    }

    fun fillList(): List<String> {
        for(i in 0..40) {
            colorList.add(randomColor())
        }
        return colorList
    }

    companion object Factory {
        fun make(color: MutableList<String>) = Color(color)
    }
}
