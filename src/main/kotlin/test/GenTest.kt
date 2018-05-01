package test

import generator.Generator
import org.knowm.xchart.QuickChart
import org.knowm.xchart.SwingWrapper
import kotlin.math.absoluteValue

object GenTest {
    fun uniformityOfDistributionTest(
            gen: Generator,
            precision: Double = 0.01,
            measurements: Int = 1_000_000
    ) {
        val counts = Array((1 / precision).toInt(), { 0 })

        for (i in 0 until measurements) {
            counts[(gen.next() / precision).toInt()]++
        }

        val values = counts.mapIndexed { index, _ -> precision * index }.toDoubleArray()

        val relativeCounts = counts.map { it.toDouble() / gen.getT() }.toDoubleArray()

        val chart = QuickChart.getChart(
                "Uniformity distribution test",
                "value",
                "p",
                "relative value",
                values,
                relativeCounts
        )

        SwingWrapper(chart).displayChart()

    }

    fun statisticIndependenceTest(
            gen: Generator,
            measurements: Int = 1_000_000
    ) {
        val samples = Array(measurements) { 0.0 }

        for (i in 0 until samples.size) {
            samples[i] = gen.next()
        }

        for (s in listOf(2, 5, 10)) {
            val r = countR(samples, s, gen.getT())
            println("For S = $s: R = $r")
        }
    }

    private fun countR(samples: Array<Double>, s: Int, t: Int): Double {
        var r = 0.0

        for (i in 0 until (samples.size - s)) {
            r += samples[i] * samples[i + s]
        }

        val tmp = (12.0 / (t - s))
        return (tmp * r)
    }

    fun calcExpectedAndDispersion(gen: Generator, measurements: Int = 10_000) {
        var expected = 0.0
        for (i in 0 until measurements) expected += gen.next() / measurements

        println("M = $expected")

        var dispersion = 0.0
        for (i in 0 until measurements) dispersion += (Math.pow(gen.next() - expected, 2.0))
        dispersion = (dispersion / measurements - 1).absoluteValue
        println("D = $dispersion")
    }
}