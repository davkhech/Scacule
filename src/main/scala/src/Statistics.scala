package src

object Statistics {
  val k = 8.3
  val eps0 = -28
  val eps1 = 0

  def apply(T: Double, meanRadius: Double, meanEnergy: Double, meanSqrEnergy: Double): Statistics =
    new Statistics(T, meanRadius, meanEnergy, meanSqrEnergy)

  def forTemperature(T: Double, molecules: List[Molecule]) = {
    val num = molecules.size
    println(s"Temperature is $T, the number of molecules $num")

    val energies = extractEnergiesFrom(molecules)
    val norm = energyNorm(energies, T)

    val meanRadius = radius(molecules, T) / norm
    val meanEnergy = energy(energies, T) / norm
    val meanSqrEnergy = sqrEnergy(energies, T) / norm
    Statistics(T, meanRadius, meanEnergy, meanSqrEnergy)
  }

  def extractEnergiesFrom(molecules: List[Molecule]) =
    molecules.map(molecule ⇒ molecule.getEnergy(eps0, eps1))

  private def energyNorm(energies: List[Double], T: Double) =
    energies.map(dE ⇒ Math.exp(-dE / (k * T))).sum

  private def energy(energies: List[Double], T: Double) =
    energies.map(dE ⇒ dE * Math.exp(-dE / (k * T))).sum

  private def sqrEnergy(energies: List[Double], T: Double) =
    energies.map(dE ⇒ dE * dE * Math.exp(-dE / (k * T))).sum

  private def radius(molecules: List[Molecule], T: Double) =
    molecules.map(molecule ⇒ {
      val dE = molecule.getEnergy(eps0, eps1)
      val dR = molecule.getRadialDistance
      dR * Math.exp(-dE / (k * T))
    }).sum
}

class Statistics(val T: Double, val meanRadius: Double, val meanEnergy: Double, val meanSqrEnergy: Double){
  override def toString: String = s"T: $T, <r>: $meanRadius, <E>: $meanEnergy, <E^2>: $meanSqrEnergy"
}