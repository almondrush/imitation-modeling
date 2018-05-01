package generator

interface Generator {
    fun next(): Double
    fun getT(): Int
}