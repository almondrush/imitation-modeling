package generator
/**
 * Multiplicative congruent sensor of basic random value
 *
 * @param m modulo
 * @param k multiplier
 * */
class MultiplicativeCongruentSensor(
        private val m: Int  = 2147483547,
        private val k: Int  = 123489754,
        initValue: Double   = Math.random()
) : Generator {
    private var value: Double = initValue

    override fun next(): Double {
        value = (k * value) % m
        return value / m
    }

    override fun getT() = m - 1

    fun printParams() = println("Multiplicative congruent sensor of BRV:\nm = $m; k = $k;")
}