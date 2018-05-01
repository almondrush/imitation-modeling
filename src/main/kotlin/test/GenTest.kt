package test

import generator.Generator


object GenTest {
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

}