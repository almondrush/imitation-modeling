package generator

interface Generator {
    fun next(): Double
    fun getT(): Int
    fun getM(measurements: Int = 10_000): Double
    fun getD(measurements: Int = 10_000): Double
}