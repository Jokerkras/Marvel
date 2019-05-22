package com.example.krasm.marvelapp

class Lab {
    companion object {
        fun sumOfElements(list: List<Int?>): Int? {
            return list.reduce({a,b -> a!! + b!!})
        }
    }
}