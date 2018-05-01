package generator

class ErlangSensor(private val lambda: Double, private val k: Int) : MultiplicativeCongruentSensor() {
    override fun next(): Double {
        var z = 0.0
        for (i in 0 until k) {
            z += super.next()
        }
        return -(Math.log(z) / lambda)
    }

    override fun getM(measurements: Int): Double = k.toDouble() / lambda
    override fun getD(measurements: Int): Double = k.toDouble() / Math.pow(lambda, 2.0)
}