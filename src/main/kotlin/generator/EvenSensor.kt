package generator

class EvenSensor(private val a: Double, private val b: Double): MultiplicativeCongruentSensor() {
    override fun next(): Double = a + (b - a) * super.next()

    override fun getM(measurements: Int): Double = a + (b - a) / 2

    override fun getD(measurements: Int): Double = Math.pow((b - a), 2.0) / 12
}