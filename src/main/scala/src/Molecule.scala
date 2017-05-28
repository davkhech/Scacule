package src

class Molecule(atoms: List[Atom]){
  def getEnergy(eps0: Double, eps1: Double) = {
    def iter(atoms: List[(Atom, Atom)], dq: Int, d: Set[Atom], s: Set[Atom]): (Int, Set[Atom], Set[Atom]) = atoms match {
      case Nil ⇒ (dq, d, s)
      case h::t ⇒
        if (h._1.y == 0 && h._2.y == 0) iter(t, dq + 1, d + h._1 + h._2, s + h._1 + h._2)
        else if (h._1.y != 0 && h._2.y == 0) iter(t, dq, d, s + h._2)
        else if (h._1.y == 0 && h._2.y != 0) iter(t, dq, d, s + h._1)
        else iter(t, dq, d, s)
    }

    val (dq, d, s) = iter(atoms zip atoms.tail, 0, Set(), Set())
    eps0 * dq + eps1 * (s.size - d.size)
  }

  def getRadialDistance = Math.sqrt(Math.pow(atoms.head.x, 2) + Math.pow(atoms.head.y, 2))

  def getX = atoms.head.x

  def getY = atoms.head.y

  override def toString: String = {
    atoms.map(atom ⇒ "(" + atom.x + ", " + atom.y + ") ").reduce(_ + _)
  }
}

object Molecule{
  def apply(atoms: List[Atom]): Molecule = new Molecule(atoms)
}
