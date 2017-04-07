package src

object Statistics {
  val k = 8.3
  val eps0 = -28
  val eps1 = 1

  def apply(meanRadius: Double, meanEnergy: Double, meanSqrEnergy: Double): Statistics =
    new Statistics(meanRadius, meanEnergy, meanSqrEnergy)

  def forTemperature(T: Double, molecules: List[Molecule]) = {
    val norm = energyNorm(molecules, T)
    val meanRadius = radius(molecules, T) / norm
    val meanEnergy = energy(molecules, T) / norm
    val meanSqrEnergy = sqrEnergy(molecules, T) / norm
    Statistics(meanRadius, meanEnergy, meanSqrEnergy)
  }

  private def energyNorm(molecules: List[Molecule], T: Double) =
    molecules.map(molecule ⇒ Math.exp(-molecule.getEnergy(eps0, eps1) / (k * T))).sum


  private def energy(molecules: List[Molecule], T: Double) =
    molecules.map(molecule ⇒ {
      val dE = molecule.getEnergy(eps0, eps1)
      dE * Math.exp(-dE / (k * T))
    }).sum

  private def sqrEnergy(molecules: List[Molecule], T: Double) =
    molecules.map(molecule ⇒ {
      val dE = molecule.getEnergy(eps0, eps1)
      dE * dE * Math.exp(-dE / (k * T))
    }).sum

  private def radius(molecules: List[Molecule], T: Double) =
    molecules.map(molecule ⇒ {
      val dE = molecule.getEnergy(eps0, eps1)
      val dR = molecule.getRadialDistance
      dR * Math.exp(-dE / (k * T))
    }).sum
}

class Statistics(val meanRadius: Double, val meanEnergy: Double, val meanSqrEnergy: Double)