import generator.*
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.XYSeries
import org.knowm.xchart.style.markers.SeriesMarkers
import test.GenTest

fun main(args: Array<String>) {
//    lab1()
    lab2()
}

fun lab1() {
    /* 1 */
    val gen = MultiplicativeCongruentSensor()
    gen.printParams()

    /* 2 */
    println("M = ${gen.getM()}")
    println("D = ${gen.getD()}")

    /* 3 */
    drawDistributionGraph(gen)

    /* 4 */
    GenTest.statisticIndependenceTest(gen)
}

fun lab2() {
    val expoGen = ExponentialSensor(2.0)
    drawDistributionGraph(expoGen, precision = 0.01)
    println("Exponential Sensor:")
    println("M = ${expoGen.getM()}")
    println("D = ${expoGen.getD()}\n")

    val evenGen = EvenSensor(3.0, 5.0)
    drawDistributionGraph(evenGen)
    println("Even Sensor:")
    println("M = ${evenGen.getM()}")
    println("D = ${evenGen.getD()}\n")

    val erlangGen = ErlangSensor(3.0, 2)
    drawDistributionGraph(erlangGen)
    println("Erlang Sensor:")
    println("M = ${erlangGen.getM()}")
    println("D = ${erlangGen.getD()}\n")

    val normalGen = NormalSensor(3.0, 2.0)
    drawDistributionGraph(normalGen)
    println("Normal Sensor:")
    println("M = ${normalGen.getM()}")
    println("D = ${normalGen.getD()}\n")

}

fun drawDistributionGraph(
        gen: Generator,
        precision: Double = 0.01,
        measurements: Int = 10_000
) {
    val samples = ArrayList<Double>(measurements)

    for (i in 0 until measurements) {
        samples.add(gen.next())
    }

    val sampleCountMap = mutableMapOf<Double, Int>()

    for (sample in samples) {
        val value = precision * (sample / precision).toInt()
        sampleCountMap[value] = sampleCountMap[value]?.plus(1) ?: 1
    }

    val values = sampleCountMap.keys.toDoubleArray()
    val relativeCounts = sampleCountMap.values.map { it.toDouble() / gen.getT() }.toDoubleArray()

    val chart = XYChartBuilder()
            .xAxisTitle("value")
            .yAxisTitle("p")
            .build()

    chart.addSeries("Relative value", values, relativeCounts)
            .marker = SeriesMarkers.CIRCLE
    chart.styler.defaultSeriesRenderStyle = XYSeries.XYSeriesRenderStyle.Scatter
    SwingWrapper(chart).displayChart()
}