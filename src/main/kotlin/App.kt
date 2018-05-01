import generator.MultiplicativeCongruentSensor
import test.GenTest

fun main(args: Array<String>) {
    lab1()
}

fun lab1() {
    /* 1 */
    val gen = MultiplicativeCongruentSensor()
    gen.printParams()

    /* 2 */
    GenTest.calcExpectedAndDispersion(gen)

    /* 3 */
    GenTest.uniformityOfDistributionTest(gen)

    /* 4 */
    GenTest.statisticIndependenceTest(gen)
}