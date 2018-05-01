package generator

import kotlin.math.absoluteValue

/**
 * Multiplicative congruent sensor of basic random value
 *
 * @param m modulo
 * @param k multiplier
 * */
open class MultiplicativeCongruentSensor(
        private val m: Int  = 2147483547,
        private val k: Int  = 123489754,
        initValue: Double   = Math.random()
) : Generator {
    private var value: Double = initValue

    private var expected = 0.0
    private var expectedIsInitialized = false

    private var dispersion = 0.0
    private var dispersionIsInitialized = false

    override fun next(): Double {
        value = (k * value) % m
        return value / m
    }

    override fun getT() = m - 1

    override fun getM(measurements: Int): Double {
        if (expectedIsInitialized) return expected
        for (i in 0 until MEASUREMENTS) expected += next() / MEASUREMENTS
        expectedIsInitialized = true
        return expected
    }

    override fun getD(measurements: Int): Double {
        if (dispersionIsInitialized) return dispersion
        for (i in 0 until measurements) dispersion += (Math.pow(next() - expected, 2.0))
        dispersion = (dispersion / measurements - 1).absoluteValue
        dispersionIsInitialized = true
        return dispersion
    }

    fun printParams() = println("Multiplicative congruent sensor of BRV:\nm = $m; k = $k;")

    companion object {
        const val MEASUREMENTS = 10_000
    }
}