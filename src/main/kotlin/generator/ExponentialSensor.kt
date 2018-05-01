package generator

class ExponentialSensor(private val lambda: Double): MultiplicativeCongruentSensor() {
    override fun next(): Double = (Math.log(super.next()) / lambda)

    override fun getM(measurements: Int): Double = 1.0 / lambda

    override fun getD(measurements: Int): Double = 1.0 / Math.pow(lambda, 2.0)
}