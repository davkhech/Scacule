package src

class Polymer(molecules: List[Molecule]){
  def getEnergy(eps0: Double, eps1: Double) = {
    def iter(molecules: List[(Molecule, Molecule)], dq: Int, d: Set[Molecule], s: Set[Molecule]): (Int, Set[Molecule], Set[Molecule]) = molecules match {
      case Nil ⇒ (dq, d, s)
      case h::t ⇒
        if (h._1.y == 0 && h._2.y == 0) iter(t, dq + 1, d + h._1 + h._2, s + h._1 + h._2)
        else if (h._1.y != 0 && h._2.y == 0) iter(t, dq, d, s + h._2)
        else if (h._1.y == 0 && h._2.y != 0) iter(t, dq, d, s + h._1)
        else iter(t, dq, d, s)
    }

    val (dq, d, s) = iter(molecules zip molecules.tail, 0, Set(), Set())
    eps0 * dq + eps1 * (s.size - d.size)
  }

  def getRadialDistance = Math.sqrt(Math.pow(molecules.head.x, 2) + Math.pow(molecules.head.y, 2))

  def getX = molecules.head.x

  def getY = molecules.head.y

  def getCoordinatesX = molecules map {molecule ⇒ molecule.x}

  def getCoordinatesY = molecules map {molecule ⇒ molecule.y}

  override def toString: String = {
    molecules.map(molecule ⇒ "(" + molecule.x + ", " + molecule.y + ") ").reduce(_ + _)
  }
}

object Polymer{
  def apply(molecules: List[Molecule]): Polymer = new Polymer(molecules)
}
