package src

object Statistics {
  val k = 0.002
  val eps0 = -28
  val eps1 = -28

  def apply(T: Double, meanRadius: Double, meanXSqr: Double, meanYSqr: Double, meanEnergy: Double, meanSqrEnergy: Double): Statistics =
    new Statistics(T, meanRadius, meanXSqr, meanYSqr, meanEnergy, meanSqrEnergy)

  def forTemperature(T: Double, polymers: List[Polymer]) = {
    val num = polymers.size
    println(s"Temperature is $T, the number of molecules $num")

    val energies = extractEnergiesFrom(polymers)
    val norm = energyNorm(energies, T)

//    val meanRadius = radius(molecules, T) / norm
//    val meanXSqr = meanX(molecules, T) / norm
//    val meanYSqr = meanY(molecules, T) / norm

    val meanRXY = meanDist(polymers, T)
    val meanRadius = meanRXY._1 / norm
    val meanXSqr = meanRXY._2 / norm
    val meanYSqr = meanRXY._3 / norm

    val meanEnergy = energy(energies, T) / norm
    val meanSqrEnergy = sqrEnergy(energies, T) / norm
    Statistics(T, meanRadius, meanXSqr, meanYSqr, meanEnergy, meanSqrEnergy)
  }

  def extractEnergiesFrom(polymers: List[Polymer]) =
    polymers.map(polymer ⇒ polymer.getEnergy(eps0, eps1))

  private def energyNorm(energies: List[Double], T: Double) =
    energies.map(dE ⇒ Math.exp(-dE / (k * T))).sum

  private def energy(energies: List[Double], T: Double) =
    energies.map(dE ⇒ dE * Math.exp(-dE / (k * T))).sum

  private def sqrEnergy(energies: List[Double], T: Double) =
    energies.map(dE ⇒ dE * dE * Math.exp(-dE / (k * T))).sum

  private def radius(polymers: List[Polymer], T: Double) =
    polymers.map(molecule ⇒ {
      val dE = molecule.getEnergy(eps0, eps1)
      val dR = molecule.getRadialDistance
      dR * Math.exp(-dE / (k * T))
    }).sum

  private def meanX(polymers: List[Polymer], T: Double) =
    polymers.map(polymer ⇒ {
      val dE = polymer.getEnergy(eps0, eps1)
      val x = polymer.getX
      x * x * Math.exp(-dE / (k * T))
    }).sum

  private def meanY(polymers: List[Polymer], T: Double) =
    polymers.map(polymer ⇒ {
      val dE = polymer.getEnergy(eps0, eps1)
      val y = polymer.getY
      y * y * Math.exp(-dE / (k * T))
    }).sum

  private def meanDist(polymers: List[Polymer], T: Double) =
    polymers.map { polymer ⇒
      val dE = polymer.getEnergy(eps0, eps1)
      val dR = polymer.getRadialDistance
      val x = polymer.getX
      val y = polymer.getY
      val e = Math.exp(-dE / (k * T))

      (dR * e, x * x * e, y * y * e)
    }.reduce { (f, s) ⇒
      (f._1 + s._1, f._2 + s._2, f._3 + s._3)
    }
}

class Statistics(val T: Double, val meanRadius: Double, val meanXSqr: Double, val meanYSqr: Double, val meanEnergy: Double, val meanSqrEnergy: Double){
  override def toString: String = s"T: $T, <r>: $meanRadius, <x^2>: $meanXSqr, <y^2>: $meanYSqr, <E>: $meanEnergy, <E^2>: $meanSqrEnergy"
}