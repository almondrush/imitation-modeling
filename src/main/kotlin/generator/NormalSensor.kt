package generator

class NormalSensor(val m: Double, val delta: Double) : MultiplicativeCongruentSensor() {

    private var preGenerated: Double? = null

    override fun next(): Double {
        val tmp = preGenerated
        preGenerated = null
        if (tmp != null) return tmp

        val z1 = super.next()
        val z2 = super.next()

        preGenerated = calc(z2, z1)
        return calc(z1, z2)
    }

    private fun calc(z1: Double, z2: Double) = Math.sqrt(-2 * Math.log(z1)) * Math.sin(2 * Math.PI * z2)


    override fun getM(measurements: Int): Double = m
    override fun getD(measurements: Int): Double = Math.pow(delta, 2.0)
}